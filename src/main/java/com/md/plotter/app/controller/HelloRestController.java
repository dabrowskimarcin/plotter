package com.md.plotter.app.controller;

import com.md.plotter.app.harvester.DataHarvester;
import com.md.plotter.app.model.Hello;
import com.md.plotter.app.repository.HelloRepository;
import com.md.plotter.app.repository.PlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/hellos")
public class HelloRestController {
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
    public Collection<Hello> hellos() throws IOException {
        plotRepository.save(harvester.harvest());

        return helloRepository.findAllByOrderByIdDesc();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable String name) {

        Hello result = helloRepository.save(new Hello(name,
                new Date()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{helloid}", method = RequestMethod.GET)
    public Hello get(@PathVariable Long helloid) {
        return this.helloRepository.findOne(helloid);
    }
}
