package com.md.plotter.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class City extends AbstractBaseEntity {
    private String name;
    private String voivodeship;
}
