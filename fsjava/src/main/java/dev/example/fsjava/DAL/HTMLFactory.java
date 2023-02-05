package dev.example.fsjava.DAL;

public class HTMLFactory {
    public static IHTMLDocument getInstances(HTMLType type){
        if(type.equals(HTMLType.Weather)){return new HTMLAccessWeather();}
        if (type.equals(HTMLType.Prayers)) {return new HTMLAccessPrayers();}

        return null;
    }
}
