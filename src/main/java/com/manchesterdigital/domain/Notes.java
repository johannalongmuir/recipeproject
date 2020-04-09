package com.manchesterdigital.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // no cascade here as if delete not want to delete the recipe
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}
