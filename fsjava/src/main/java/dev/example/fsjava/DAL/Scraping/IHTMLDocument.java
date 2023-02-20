package dev.example.fsjava.DAL.Scraping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public interface IHTMLDocument {
    public <T> List<T> getData(String url) throws IOException;
}
