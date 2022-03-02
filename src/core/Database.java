package core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import core.interfaces.IDatabase;
import models.Compte;
import models.interfaces.IEntity;

/**
 * Database
 */
public class Database implements IDatabase {

    private String _dbUrl = "jdbc:mysql://localhost:3306/jeu";
    private String user = "root";
    private String password = "";
    private Connection connection;
    private String query;
    private Object entity;
    private String entityName;
    private List<Object> params = new ArrayList<Object>();
    private PreparedStatement stmt;
    private boolean isDisposable = false;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IDatabase select(String modelName) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(this._dbUrl, this.user, this.password);
            Class<?> obj = Class.forName("models." + modelName);
            this.entity = obj.newInstance();
            this.entityName = modelName;
        } catch (Exception e) {
            this.dispose();
            throw new ClassCastException("Unknown model: " + modelName);
        }

        this.query = "";
        this.query += "SELECT ";
        this.query += "* ";
        this.query += "FROM " + modelName;

        return this;
    }

    @Override
    public IDatabase where(Map<String, Object> filters) throws Exception {
        int count = 0;
        if (filters == null) {
            this.dispose();
            throw new Exception("filters can not be null");
        }

        this.query += " WHERE ";

        Field[] props = this.entity.getClass().getDeclaredFields();

        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            for (Field field : props) {
                if (filter.getKey() == field.getName()) {
                    this.query += filter.getKey() + " = ? ";
                    this.params.add(filter.getValue());
                    count++;
                    break;
                } else {
                    continue;
                }
            }
            if (count < filters.entrySet().size()) {
                this.query += "AND ";
            }
        }
        this.query += ";";
        return this;
    }

    @Override
    public IDatabase Insert(Compte nouveauJoueur) throws InstantiationException, IllegalAccessException, SQLException {
        this.connection = DriverManager.getConnection(this._dbUrl, this.user, this.password);

        int count = 0;
        this.query = "INSERT INTO ";
        Class<?> obj = nouveauJoueur.getClass();
        this.entity = obj.newInstance();
        this.entityName = obj.getName();
        String[] modelName = this.entityName.split(Pattern.quote("."));
        this.query += modelName[modelName.length - 1] + " ";
        this.query += "(";
        String valueQuery = " VALUES (";

        Field[] values = nouveauJoueur.getClass().getDeclaredFields();
        for (Field field : values) {
            count++;
            field.setAccessible(true);
            this.query += field.getName();
            valueQuery += "?";
            this.params.add(field.get(nouveauJoueur));
            if (count < values.length) {
                this.query += ", ";
                valueQuery += ", ";
            }

        }
        this.query += ")";
        valueQuery += ")";
        this.query += valueQuery;

        this.isDisposable = true;
        return this;
    }

    @Override
    public IDatabase excuteQuery() throws SQLException {
        try {
            this.stmt = this.connection.prepareStatement(this.query);
            if (this.params != null) {
                for (int x = 0; x < params.size(); x++) {
                    this.stmt.setObject(x + 1, params.get(x));
                }
            }
            if (isDisposable) {
                int res = this.stmt.executeUpdate();
                System.out.println(res);
                this.dispose();
            }
        } catch (Exception e) {
            this.dispose();
            throw new SQLException();
        }
        return this;
    }

    @Override
    public IEntity First() throws SQLException {
        try {
            ResultSet result = this.stmt.executeQuery();
            if (!result.next()) {
                this.dispose();
                return null;
            }
            Class<?> obj = Class.forName("models." + entityName);
            this.entity = obj.newInstance();
            Field[] fields = this.entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (result.getObject(field.getName()) != null) {
                    field.setAccessible(true);
                    field.set(entity, result.getObject(field.getName()));
                }
            }
            IEntity entityResult = (IEntity) this.entity;
            this.dispose();
            return entityResult;
        } catch (Exception e) {
            this.dispose();
            throw new SQLException();
        }
    }

    @Override
    public List<IEntity> ToList(List<IEntity> entities) throws SQLException {
        try {
            ResultSet result = this.stmt.executeQuery();
            if (!result.next()) {
                this.dispose();
                return null;
            } else {
                do {
                    Class<?> obj = Class.forName("models." + entityName);
                    this.entity = obj.newInstance();
                    Field[] fields = this.entity.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if (result.getObject(field.getName()) != null) {
                            field.set(entity, result.getObject(field.getName()));
                        }
                    }
                    entities.add((IEntity) this.entity);
                } while (result.next());
            }
            this.dispose();
            return entities;
        } catch (Exception e) {
            this.dispose();
            throw new SQLException();
        }
    }

    @Override
    public void dispose() throws SQLException {
        this.query = null;
        this.isDisposable = false;
        this.entity = null;
        this.entityName = null;
        this.params = new ArrayList<Object>();
        this.stmt = null;
        try {
            this.connection.close();
        } catch (Exception e) {
            throw new SQLException();
        }
    }
}
