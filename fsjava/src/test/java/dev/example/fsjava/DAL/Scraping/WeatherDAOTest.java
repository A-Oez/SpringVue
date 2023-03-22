package dev.example.fsjava.DAL.Scraping;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Element;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class WeatherDAOTest {
    private static final WeatherDAO daoWeather = new WeatherDAO();

    @Test
    void checkListEntry() throws IOException {
        String place = "Hamburg";
        String time = "14:14";
        List<Element> daoWeatherList = daoWeather.getData("https://www.google.de/search?q=wetter+" + place + "+" + time + "+" + "Uhr");

        assertFalse(daoWeatherList.size() != 2, "weather list doesnt have the size of two elements");

        String status = daoWeatherList.get(0).text();
        String temperature = daoWeatherList.get(1).text();

        assertTrue(!status.isEmpty() && !temperature.isEmpty(),"list elements are empty");
    }
}