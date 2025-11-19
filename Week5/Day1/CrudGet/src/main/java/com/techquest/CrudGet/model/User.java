package main.java.com.techquest.CrudGet.model;

import java.beans.ConstructorProperties;


public class User {
    @ConstructorProperties({"id", "name", "email"})
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    private int id;
    private String name;        
    private String email;
    public int getId() {
        return id;
    }               
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
