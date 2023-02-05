package dev.example.fsjava.controller;

import dev.example.fsjava.service.ScraperServicePrayers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class PrayersControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getTimeIntegrationTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/time");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("Hello",result.getResponse().getContentAsString());
    }

    @Test
    void getTimeUnitTest() throws Exception {
        PrayersController controller = new PrayersController();
        //ScraperServicePrayers prayers = new ScraperServicePrayers();

        //tring response = controller.getTime(prayers);
        //assertTrue(response.contains("17:18"));
    }
}