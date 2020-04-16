package com.manchesterdigital.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setup(){
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 1L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getDescription() {
        category.setDescription("Test Description");
        assertEquals("Test Description", category.getDescription());
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(Recipe.builder().description("Recipe One").build());
        recipes.add(Recipe.builder().description("Recipe Two").build());
        category.setRecipes(recipes);
        assertEquals(2, category.getRecipes().size());
    }
}