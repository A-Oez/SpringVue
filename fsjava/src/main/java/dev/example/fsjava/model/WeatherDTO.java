package dev.example.fsjava.model;

public class WeatherDTO {
    private String status;
    private String temperature;
    private String image;


    public WeatherDTO(String status, String temperature, String image){
        this.status = status;
        this.temperature = temperature;
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
