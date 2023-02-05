package dev.example.fsjava.DAL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HTMLAccessPrayers implements IHTMLDocument {
    @Override
    public List<Elements> getData(String url) throws IOException {
        List<Elements> values = new ArrayList<>();
        Document document = Jsoup.connect(url).get();

        values.add(document.getElementsByClass("im3"));
        return values;
    }
}
