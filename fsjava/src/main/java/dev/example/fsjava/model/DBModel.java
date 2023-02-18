package dev.example.fsjava.model;

public class DBModel {
    private String ID;
    private String type;
    private String value;


    public DBModel(String id, String type, String value) {
        ID = id;
        this.type = type;
        this.value = value;
    }

    public String getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
