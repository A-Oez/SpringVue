package dev.example.fsjava;

import dev.example.fsjava.DAL.Scraping.HTMLType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    @Qualifier("TypePrayers")
    public HTMLType htmlTypePrayers() {return HTMLType.Prayers;}

    @Bean
    @Qualifier("TypeWeather")
    public HTMLType htmlTypeWeather() {return HTMLType.Weather;}
}
