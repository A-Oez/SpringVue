package dev.example.fsjava.DAL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HTMLAccessWeather implements IHTMLDocument {
    @Override
    public List<Element> getData(String url) throws IOException {
        List<Element> values = new ArrayList<>();
        Document document = Jsoup.connect(url).get();

        values.add(document.getElementById("wob_dc"));
        values.add(document.getElementById("wob_tci"));
        values.add(document.getElementById("wob_tm"));

        return values;
    }
}