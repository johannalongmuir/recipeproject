package com.manchesterdigital.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal amount;


    @OneToOne(fetch = FetchType.EAGER) //maybe the default. But want it to get it every time from the database
    private UnitOfMeasure uom;

    @ManyToOne // Many ingredients for one recipe
    private Recipe recipe;

}
