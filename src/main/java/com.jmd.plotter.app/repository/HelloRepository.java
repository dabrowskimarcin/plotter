package com.jmd.plotter.app.repository;

import com.jmd.plotter.app.model.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelloRepository extends JpaRepository<Hello, Long>{
	
	List<Hello> findAllByOrderByIdDesc();

}
