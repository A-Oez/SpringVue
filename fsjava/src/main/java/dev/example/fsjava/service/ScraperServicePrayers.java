package dev.example.fsjava.service;

import dev.example.fsjava.DAL.HTMLFactory;
import dev.example.fsjava.DAL.HTMLType;
import dev.example.fsjava.DAL.IHTMLDocument;
import dev.example.fsjava.model.PrayingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperServicePrayers {
    private final static String url = "https://www.namaztakvimi.com/almanya/gundelsheim-ezan-vakti.html";
    private static PrayingDTO dto = new PrayingDTO();
    private static List<String> valueList = new ArrayList<>();
    private IHTMLDocument document;

    @Autowired
    public ScraperServicePrayers(@Qualifier("TypePrayers") HTMLType type){
        this.document = HTMLFactory.getInstances(type);
    }

    public String extractData() throws IOException {
        if(valueList.size() != 0){return JsonCreater.create(dto);}
        Elements elements = (Elements) document.getData(url).get(0);
        for(Element ads : elements){valueList.add(ads.text());}
        setDTOAttributes();
        return JsonCreater.create(dto);
    }

    private void setDTOAttributes(){
        //first index = description
        valueList.remove(0);
        dto.setSunrise(valueList.get(0));
        dto.setMorning(valueList.get(1));
        dto.setNoon(valueList.get(2));
        dto.setAfternoon(valueList.get(3));
        dto.setEvening(valueList.get(4));
        dto.setNight(valueList.get(5));
    }
}