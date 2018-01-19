package com.sagarandcompany.example;

import java.io.Serializable;

public class User implements Serializable{
    private Long id;
    private String name;
    private String password;
    private String emailAddress;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getEmailAddress () {
        return emailAddress;
    }

    public void setEmailAddress (String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString () {
        return "User{" +
                            "id=" + id +
                            ", name='" + name + '\'' +
                            ", password='" + password + '\'' +
                            ", emailAddress='" + emailAddress + '\'' +
                            '}';
    }
}