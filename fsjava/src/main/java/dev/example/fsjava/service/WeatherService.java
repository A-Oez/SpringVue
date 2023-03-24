package dev.example.fsjava.service;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.Scraping.HTMLFactory;
import dev.example.fsjava.DAL.Scraping.HTMLType;
import dev.example.fsjava.DAL.Scraping.IHTMLDocument;
import dev.example.fsjava.DTO.WeatherDTO;
import dev.example.fsjava.logger.LogInbound;
import dev.example.fsjava.logger.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.List;


@Service
public class WeatherService {
    private IHTMLDocument document;

    @Autowired
    public WeatherService(@Qualifier("TypeWeather") HTMLType type){this.document = HTMLFactory.getInstances(type);}

    public String extractData(String place, String time, HttpServletRequest request) throws IOException {
        LogInbound inbound = LogService.createInbound(request.getRequestURI(), request);
        String body = "";

        try{
            String url = "https://www.google.de/search?q=wetter+" + place + "+" + time + "+" + "Uhr";
            List<Element> list = document.getData(url);
            String status = list.get(0).text();
            String temperature = list.get(1).text();
            body = new Gson().toJson(new WeatherDTO(status,temperature));

            LogService.logger(inbound,"SUCCESS");
        }
        catch(Exception ex){
            LogService.logger(inbound,ex.getMessage());
        }

        return body;
    }
}