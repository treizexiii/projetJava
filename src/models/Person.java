package models;

import java.sql.ResultSet;
import models.interfaces.IEntity;

public class Person implements IEntity {
    private int id;
    private String firstname;
    private String lastname;
    private int age;

    // public Person(String firstname, String lastname, int age) {
    // this.setFirstname(firstname);
    // this.setLastname(lastname);
    // this.setAge(age);
    // }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Object deserialize(ResultSet rawData, int columnCount) {
        return null;
    }

    @Override
    public String serrialize() {
        String json = "{\n";
        json += "   \"id\": " + this.id + ",\n";
        json += "   \"firstname\": " + this.firstname + ",\n";
        json += "   \"lastname\": " + this.lastname + ",\n";
        json += "   \"age\": " + this.age + ",\n";
        json += "}";
        return json;
    }
}
