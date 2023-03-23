package dev.example.fsjava.controller;

import dev.example.fsjava.DAL.Scraping.HTMLType;
import dev.example.fsjava.service.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/weather")
public class WeatherController {
    private static WeatherService serviceWeather = new WeatherService(HTMLType.Weather);
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @GetMapping("/data")
    public String weather(@RequestParam String place, @RequestParam String time, HttpServletRequest request) throws IOException {
        String input = String.valueOf(request.getInputStream());
        return serviceWeather.extractData(place, time, request);
    }
}
