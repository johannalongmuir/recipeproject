package com.manchesterdigital.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Recipe {

    @Id // Special type for relational. Auto_Generated primary key etc.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    private String source;

    private String url;

    private String directions;

    //TODO add difficulty
    //private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") //mapped by is target property on ingredient class.
    private Set<Ingredient> ingredients;

    @Lob // will create as Binary Large Object
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL) // Sets Recipe as the owner for this relationship. If delete recipe will delete notes.
    private Notes notes;

}
