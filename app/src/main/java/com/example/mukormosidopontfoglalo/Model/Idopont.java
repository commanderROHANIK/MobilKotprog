package com.example.mukormosidopontfoglalo.Model;

public class Idopont {
    private String id;
    private String userID;
    private String idopont;

    public Idopont(String userID, String idopont) {
        this.id = userID + idopont.join(" ");
        this.userID = userID;
        this.idopont = idopont;
    }

    public Idopont(String id, String userID, String idopont) {
        this.id = id;
        this.userID = userID;
        this.idopont = idopont;
    }

    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getIdopont() {
        return idopont;
    }
}
