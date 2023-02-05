package dev.example.fsjava.service;

import dev.example.fsjava.DAL.HTMLFactory;
import dev.example.fsjava.DAL.HTMLType;
import dev.example.fsjava.DAL.IHTMLDocument;
import dev.example.fsjava.model.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.List;


@Service
public class ScraperServiceWeather {
    private static WeatherDTO dto = new WeatherDTO();
    private IHTMLDocument document;

    @Autowired
    public ScraperServiceWeather(@Qualifier("TypeWeather") HTMLType type){this.document = HTMLFactory.getInstances(type);}

    public String extractData(String place, String time) throws IOException {
        String url = "https://www.google.de/search?q=wetter+" + place + "+" + time + "+" + "Uhr";
        List<Element> list = document.getData(url);
        String status = list.get(0).text();
        String image = list.get(1).attributes().get("src");
        String temperature = list.get(2).text();
        setDTOAttributes(status,temperature,image);
        return JsonCreater.create(dto);
    }

    private void setDTOAttributes(String status, String temperature, String image){
        dto.setStatus(status);
        dto.setTemperature(temperature);
        dto.setImage(image);
    }
}