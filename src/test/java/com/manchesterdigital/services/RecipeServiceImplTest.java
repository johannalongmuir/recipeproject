package com.manchesterdigital.services;

import com.manchesterdigital.domain.Recipe;
import com.manchesterdigital.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private final RecipeRepository recipeRepositoryMock = mock(RecipeRepository.class);

    RecipeService recipeService = new RecipeServiceImpl(recipeRepositoryMock);

    @Test
    void getRecipes() {
        when(recipeRepositoryMock.findAll()).thenReturn(List.of(Recipe.builder().description("One").build()));
        List<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1, recipes.size());
        verify(recipeRepositoryMock, times(1)).findAll(); //called one time
    }
}