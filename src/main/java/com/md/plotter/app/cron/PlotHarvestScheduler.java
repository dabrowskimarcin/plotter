package com.md.plotter.app.cron;

import com.md.plotter.app.model.Plot;
import com.md.plotter.app.repository.PlotRepository;
import com.md.plotter.app.service.CityService;
import com.md.plotter.app.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PlotHarvestScheduler {
    @Autowired
    private PlotService plotService;

    @Autowired
    private CityService cityService;

    @Autowired
    private PlotRepository plotRepository;

    public void schedule() throws IOException {
        final List<Plot> plots = plotService.preparePlots();
        cityService.updateCities(plots);
        plotRepository.save(plots);
    }
}
