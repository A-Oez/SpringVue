package dev.example.fsjava.DAL.Scraping;

public class HTMLFactory {
    public static IHTMLDocument getInstances(HTMLType type){
        if(type.equals(HTMLType.Weather)){return new WeatherDAO();}
        if (type.equals(HTMLType.Prayers)) {return new PrayersDAO();}

        return null;
    }
}
