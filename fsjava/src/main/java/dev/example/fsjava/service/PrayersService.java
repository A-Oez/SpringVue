package dev.example.fsjava.service;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.Scraping.HTMLFactory;
import dev.example.fsjava.DAL.Scraping.HTMLType;
import dev.example.fsjava.DAL.Scraping.IHTMLDocument;
import dev.example.fsjava.DTO.PrayingDTO;
import dev.example.fsjava.logger.LogInbound;
import dev.example.fsjava.logger.LogOutbound;
import dev.example.fsjava.logger.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrayersService {
    private final static String url = "https://www.namaztakvimi.com/almanya/gundelsheim-ezan-vakti.html";
    private static PrayingDTO dto = new PrayingDTO();
    private static List<String> valueList = new ArrayList<>();
    private IHTMLDocument document;

    private static String Date;

    @Autowired
    public PrayersService(@Qualifier("TypePrayers") HTMLType type){
        this.document = HTMLFactory.getInstances(type);
    }

    public String extractData(HttpServletRequest request) throws IOException {
        LogInbound inbound = LogService.createInbound(request.getRequestURI(), request);
        String body = "";

        try {
            if(valueList.size() != 0 && getDate(valueList.get(0))){return new Gson().toJson(dto);}
            Elements elements = (Elements) document.getData(url).get(0);
            for(Element ads : elements){valueList.add(ads.text());}
            setDTOAttributes();
            body = new Gson().toJson(dto);

            LogOutbound outbound = LogService.createOutbound(body);
            LogService.logger(inbound,outbound,"SUCCESS");
        }
        catch (Exception ex){
            LogOutbound outbound = LogService.createOutbound(ex.getMessage());
            LogService.logger(inbound,outbound,ex.getMessage());
        }

        return body;
    }

    private boolean getDate(String elements){
        boolean check = false;
        if(elements.contains(String.valueOf(LocalDate.now().getDayOfMonth()))){check = true;}
        return check;
    }

    private void setDTOAttributes(){
        //first index = description
        String Date = valueList.get(0);
        dto.setSunrise(valueList.get(1));
        dto.setMorning(valueList.get(2));
        dto.setNoon(valueList.get(3));
        dto.setAfternoon(valueList.get(4));
        dto.setEvening(valueList.get(5));
        dto.setNight(valueList.get(6));
    }
}