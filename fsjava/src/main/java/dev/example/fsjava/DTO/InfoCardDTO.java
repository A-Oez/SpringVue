package dev.example.fsjava.DTO;

public class InfoCardDTO {
    private int ID;
    private String type;
    private String value;

    private boolean check;

    public InfoCardDTO() {}

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

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
