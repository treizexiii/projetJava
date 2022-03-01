package models;

import java.sql.ResultSet;
import models.interfaces.IEntity;

public class Compte implements IEntity {

    private int id;
    private String Nom;
    private String Prenom;
    private int Age;
    private String Login;
    private String Motdepasse;

    public String getMotdepasse() {
        return Motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.Motdepasse = motdepasse;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        this.Login = login;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        this.Prenom = prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Object deserialize(ResultSet rawData, int columnCount) {
        return null;
    }

    @Override
    public String serrialize() {
        return null;
    }

}
