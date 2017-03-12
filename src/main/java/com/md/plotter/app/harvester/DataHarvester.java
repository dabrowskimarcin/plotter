package com.md.plotter.app.harvester;

import com.md.plotter.app.model.Plot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
public class DataHarvester {

    public List<Plot> harvest() throws IOException {
        final String url = "https://www.otodom.pl/sprzedaz/dzialka/wroclaw/?search%5Bdescription%5D=1&search%5Bpaidads_listing%5D=1&search%5Bdist%5D=75&nrAdsPerPage=72";
        Document document = Jsoup.connect(url).get();
        return new PlotDataConverter().parse(document);
    }
}
