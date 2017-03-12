package com.md.plotter.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
public class Plot extends AbstractBaseEntity {
    private BigDecimal price;
    private BigDecimal meterPrice;
    private BigDecimal area;
    private Date createDate;
    private String href;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private City city;

}
