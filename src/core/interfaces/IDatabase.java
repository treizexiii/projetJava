package core.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import models.interfaces.IEntity;

/**
 * IDatabase
 */
public interface IDatabase {
    IDatabase select(String modelName) throws SQLException;

    IDatabase where(Map<String, Object> filters) throws Exception;

    IDatabase Insert(IEntity entity) throws InstantiationException, IllegalAccessException, SQLException;

    IDatabase Update(IEntity entity) throws InstantiationException, IllegalAccessException, SQLException;

    IDatabase excuteQuery() throws SQLException;

    IEntity First() throws SQLException;

    List<IEntity> ToList() throws SQLException;

    void dispose() throws SQLException;
}