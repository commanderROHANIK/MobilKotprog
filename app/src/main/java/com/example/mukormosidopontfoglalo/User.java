package com.example.mukormosidopontfoglalo;

public class User {
    private String id;
    private String email;
    private String username;

    public User(String email, String username) {
        this.id = email.split("@")[0];
        this.email = email;
        this.username = username;
    }

    public User(String id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
