package com.md.plotter.app.repository;

import com.md.plotter.app.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository<Plot, String> {
}
