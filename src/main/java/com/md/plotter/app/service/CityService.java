package com.md.plotter.app.service;

import com.md.plotter.app.model.City;
import com.md.plotter.app.model.Plot;
import com.md.plotter.app.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public void updateCities(final List<Plot> plots) {
        final Map<String, City> cities = cityRepository.findAll()
                .stream()
                .collect(toMap(City::getName, x -> x));
        plots.forEach(plot -> plot.setCity(ofNullable(plot.getCity()).orElse(determineCity(plot, cities))));
    }

    private City determineCity(final Plot plot,
                               final Map<String, City> cities) {
        final String cityName = cutCity(plot.getTitle());
        final City c = cities.get(cityName);
        final City city = c != null ? c : createCity(cityName);
        cities.put(cityName, city);
        return city;
    }

    private City createCity(final String cityName) {
        final City city = new City();
        city.setName(cityName);
        return cityRepository.save(city);
    }

    public String cutCity(final String plotTitle) {
        return plotTitle.replaceAll(".*:|,.*", "")
                .trim();
    }
}
