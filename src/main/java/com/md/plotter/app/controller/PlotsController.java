package com.md.plotter.app.controller;

import com.md.plotter.app.harvester.DataHarvester;
import com.md.plotter.app.model.Plot;
import com.md.plotter.app.repository.HelloRepository;
import com.md.plotter.app.repository.PlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/plots")
public class PlotsController {

    @Autowired
    private HelloRepository helloRepository;

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private DataHarvester harvester;

    @Autowired
    public void setHelloRepository(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Plot> plots() throws IOException {
        final List<Plot> harvest = harvester.harvest();
        plotRepository.save(harvest);
        return harvest;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long count() {
        return plotRepository.count();
    }

//    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
//    public ResponseEntity<?> add(@PathVariable String name) {
//
//        Hello result = helloRepository.save(new Hello(name,
//                new Date()));
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(result.getId()).toUri());
//        return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/{helloid}", method = RequestMethod.GET)
//    public Hello get(@PathVariable Long helloid) {
//        return this.helloRepository.findOne(helloid);
//    }
}
