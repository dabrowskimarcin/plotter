package com.md.plotter.app.repository;

import com.md.plotter.app.model.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelloRepository extends JpaRepository<Hello, Long> {
    List<Hello> findAllByOrderByIdDesc();
}
