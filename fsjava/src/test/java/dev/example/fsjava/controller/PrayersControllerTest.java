package dev.example.fsjava.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrayersControllerTest {
    @Test
    void getTimeUnitTest() throws Exception {
        PrayersController controller = new PrayersController();
        String response = controller.getTime();
        assertTrue(response.contains("17:50"));
    }
}