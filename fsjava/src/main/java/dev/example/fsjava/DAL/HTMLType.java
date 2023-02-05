package dev.example.fsjava.DAL;

import org.springframework.beans.factory.annotation.Qualifier;

public enum HTMLType {
    @Qualifier("TypeWeather")
    Weather,
    @Qualifier("TypePrayers")
    Prayers
}