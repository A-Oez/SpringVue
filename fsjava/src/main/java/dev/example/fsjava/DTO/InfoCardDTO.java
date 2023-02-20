package dev.example.fsjava.DTO;

public class InfoCardDTO {
    private int ID;
    private String type;
    private String value;

    private boolean check;


    public InfoCardDTO(String type, String value, boolean check) {
        this.type = type;
        this.value = value;
        this.check = check;
    }

    public InfoCardDTO(int ID, String type, String value, boolean check) {
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
