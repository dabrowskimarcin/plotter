package com.md.plotter.app.harvester;

import com.md.plotter.app.model.Plot;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.md.plotter.app.harvester.CommonConstants.*;
import static java.lang.Integer.parseInt;
import static org.jsoup.Jsoup.connect;

@Component
public class DataHarvester {

    private final int DEFAULT_PAGE_NUMBER = 1;
    private final String DEFAULT_PLOT_SEARCH = "sprzedaz/dzialka/";

    @Value("${application.search.domain}")
    private String domain;

    @Value("${application.search.city}")
    private String city;

    @Value("${application.search.configuration}")
    private String conf;

    @Value("${application.search.page}")
    private String page;

    @Value("${application.search.nr.addr.per.page}")
    private Integer addressesNumber;

    @Autowired
    private PlotDataConverter converter;

    public List<Plot> harvest() throws IOException {
        final String url = prepareURL(DEFAULT_PAGE_NUMBER);
        final Document document = connect(url).get();
        final List<Plot> plots = converter.parse(document);
        final Integer offersNumber = readOfferNumber(document);

        final AtomicInteger counter = new AtomicInteger(1);
        while (true) {
            if (plots.size() >= offersNumber) {
                break;
            }
            System.out.println(counter + " " + plots.size());
            plots.addAll(converter.parse(connect(prepareURL(counter.incrementAndGet())).get()));
        }

        return plots;
    }

    private Integer readOfferNumber(final Document document) {
        return parseInt(document.getElementsByClass("offers-index").get(0)
                .text()
                .replaceAll(IGNORE_NON_DIGIT_PATTERN, "")
                .trim());
    }

    private String prepareURL(final int pageNumber) {
        return new StringBuilder(domain)
                .append(DEFAULT_PLOT_SEARCH)
                .append(city)
                .append(conf)
                .append(addressesNumber)
                .append(page)
                .append(pageNumber)
                .toString();
    }
}
