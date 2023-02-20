package dev.example.fsjava.controller;

import dev.example.fsjava.DAL.Scraping.HTMLType;
import dev.example.fsjava.service.ScraperServicePrayers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/prayers")
public class PrayersController {
    private static ScraperServicePrayers servicePrayers = new ScraperServicePrayers(HTMLType.Prayers);

    @GetMapping("/time")
    public String getTime() throws IOException {
        return servicePrayers.extractData();
    }
}
