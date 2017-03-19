package com.md.plotter.app.controller;

import com.md.plotter.app.cron.PlotHarvestScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/plots")
public class PlotsController {

    @Autowired
    private PlotHarvestScheduler scheduler;

    @RequestMapping(method = RequestMethod.GET)
    public void plots() throws IOException {
        scheduler.schedule();
    }
}
