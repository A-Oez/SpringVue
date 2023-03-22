package dev.example.fsjava.DAL.Scraping;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class PrayersDAOTest {

    private static final PrayersDAO prayersDao = new PrayersDAO();
    private final static String url = "https://www.namaztakvimi.com/almanya/gundelsheim-ezan-vakti.html";
    private static List<String> prayersList = new ArrayList<>();

    @Test
    void checkListEntry() throws IOException {
        Elements elements = (Elements) prayersDao.getData(url).get(0);
        //elements.remove(0);
        assertFalse(elements.isEmpty() || elements.size() != 8,"prayersDAO didnt get any values ore values are missing");
    }

    @Test
    void checkDateTime() throws IOException {
        checkListEntry();
        addValuesToList();
        //prayersList.set(0, "10 Mart 2023 Ca");
        assertFalse(!prayersList.get(0).contains(String.valueOf(LocalDate.now().getDayOfMonth())),"date in prayers list is not the current date");
    }

    private static void addValuesToList() throws IOException {
        Elements elements = prayersDao.getData(url).get(0);
        for(Element value: elements){
            prayersList.add(value.text());
        }
    }
}