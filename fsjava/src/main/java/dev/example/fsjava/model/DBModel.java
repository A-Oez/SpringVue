package dev.example.fsjava.model;

public class DBModel {
    private int ID;
    private String type;
    private String value;

    private boolean check;


    public DBModel(String type, String value, boolean check) {
        this.type = type;
        this.value = value;
        this.check = check;
    }

    public DBModel(int ID, String type, String value, boolean check) {
        this.ID = ID;
        this.type = type;
        this.value = value;
        this.check = check;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isCheck() {
        return check;
    }

    public int getID() {
        return ID;
    }
}
