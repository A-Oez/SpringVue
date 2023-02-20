package dev.example.fsjava.controller;

import dev.example.fsjava.DAL.Scraping.HTMLType;
import dev.example.fsjava.service.ScraperServiceWeather;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/weather")
public class WeatherController {
    private static ScraperServiceWeather serviceWeather = new ScraperServiceWeather(HTMLType.Weather);

    @GetMapping("/data")
    public String weather(@RequestParam String place, @RequestParam String time) throws IOException {
        return serviceWeather.extractData(place, time);
    }
}
