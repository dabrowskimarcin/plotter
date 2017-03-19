package com.md.plotter.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
public class City extends AbstractBaseEntity {
    @Column(unique = true)
    private String name;
}
