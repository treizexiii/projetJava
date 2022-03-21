package models;

import java.sql.ResultSet;

import models.interfaces.IEntity;

public class Scores implements IEntity {
    private int id;
    private String login;
    private int NbPartiesGagnees;
    private int NbPartiesPerdues;

    public int getId() {
        return id;
    }

    public int getNbPartiesPerdues() {
        return NbPartiesPerdues;
    }

    public void setNbPartiesPerdues(int nbPartiesPerdues) {
        this.NbPartiesPerdues = nbPartiesPerdues;
    }

    public int getNbPartiesGagnees() {
        return NbPartiesGagnees;
    }

    public void setNbPartiesGagnees(int nbPartiesGagnees) {
        this.NbPartiesGagnees = nbPartiesGagnees;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
