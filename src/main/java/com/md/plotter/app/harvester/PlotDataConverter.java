package com.md.plotter.app.harvester;

import com.md.plotter.app.model.Plot;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.md.plotter.app.harvester.CommonConstants.IGNORE_NON_DIGIT_PATTERN;
import static java.util.stream.Collectors.toList;

@Component
public class PlotDataConverter {

    public List<Plot> parse(final Document document) {
        return document.getElementsByClass("offer-item-details").stream().map(element -> {
            final BigDecimal price = new BigDecimal(element
                    .getElementsByClass("params")
                    .get(0).getElementsByClass("offer-item-price")
                    .get(0).text().trim()
                    .replaceAll(IGNORE_NON_DIGIT_PATTERN, "")
                    .replace(",", "."));

            final String title = element
                    .getElementsByClass("offer-item-header").get(0)
                    .getElementsByClass("text-nowrap").text();

            final BigDecimal area = new BigDecimal(element.getElementsByClass("params")
                    .get(0).getElementsByClass("offer-item-area")
                    .get(0).text().trim()
                    .replaceAll(IGNORE_NON_DIGIT_PATTERN, "")
                    .replace(",", "."));

            final BigDecimal meterPrice = new BigDecimal(element.getElementsByClass("params")
                    .get(0).getElementsByClass("offer-item-price-per-m")
                    .get(0).text().trim()
                    .replaceAll(IGNORE_NON_DIGIT_PATTERN, "")
                    .replace(",", "."));

            final Plot plot = new Plot();
            plot.setPrice(price);
            plot.setTitle(title);
            plot.setArea(area);
            plot.setMeterPrice(meterPrice);
            plot.setCreateDate(DateTime.now().toDate());
            return plot;
        }).collect(toList());
    }
}
