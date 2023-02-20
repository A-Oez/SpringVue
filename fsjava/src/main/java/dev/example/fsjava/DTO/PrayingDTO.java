package dev.example.fsjava.DTO;

public class PrayingDTO {
    private String sunrise;
    private String morning;
    private String noon;
    private String afternoon;
    private String evening;
    private String night;

    public PrayingDTO(String sunrise, String morning, String noon, String afternoon, String evening, String night){
        this.sunrise = sunrise;
        this.morning = morning;
        this.noon = noon;
        this.afternoon = afternoon;
        this.evening = evening;
        this.night = night;
    }

    public PrayingDTO(){}

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public void setEvening(String evening) {
        this.evening = evening;
    }

    public void setNight(String night) {
        this.night = night;
    }
}
