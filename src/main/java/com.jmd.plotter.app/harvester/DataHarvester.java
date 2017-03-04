package com.jmd.plotter.app.harvester;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class DataHarvester {
    public static void main(String[] args) throws IOException {

        final String url = "https://www.otodom.pl/sprzedaz/dzialka/wroclaw/?search%5Bdist%5D=10";

        Document document = Jsoup.connect(url).get();
        document.getAllElements();
    }

}
