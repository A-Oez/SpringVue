package dev.example.fsjava.DTO;

public class WeatherDTO {
    private String status;
    private String temperature;

    public WeatherDTO(String status, String temperature){
        this.status = status;
        this.temperature = temperature;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
