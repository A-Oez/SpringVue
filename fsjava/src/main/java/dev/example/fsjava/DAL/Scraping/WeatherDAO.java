package dev.example.fsjava.DAL.Scraping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WeatherDAO implements IHTMLDocument {
    @Override
    public List<Element> getData(String url) throws IOException {
        List<Element> values = new ArrayList<>();
        Document document = Jsoup.connect(url).get();

        values.add(document.getElementById("wob_dc"));
        values.add(document.getElementById("wob_tm"));

        return values;
    }
}