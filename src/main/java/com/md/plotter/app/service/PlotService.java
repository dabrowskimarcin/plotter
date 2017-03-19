package com.md.plotter.app.service;

import com.md.plotter.app.harvester.DataHarvester;
import com.md.plotter.app.model.Plot;
import com.md.plotter.app.repository.PlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PlotService {

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private DataHarvester harvester;

    public List<Plot> preparePlots() throws IOException {
        return harvester.harvest();
    }
}
