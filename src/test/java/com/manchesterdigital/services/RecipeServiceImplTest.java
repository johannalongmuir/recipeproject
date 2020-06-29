package com.manchesterdigital.services;

import com.manchesterdigital.domain.Recipe;
import com.manchesterdigital.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private final RecipeRepository recipeRepositoryMock = mock(RecipeRepository.class);

    RecipeService recipeService = new RecipeServiceImpl(recipeRepositoryMock);

    @Test
    void getRecipes() {
        when(recipeRepositoryMock.findAll()).thenReturn(List.of(
                Recipe.builder()
                        .description("One")
                        .build()));

        List<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepositoryMock, times(1)).findAll(); //called one time
        verify(recipeRepositoryMock, never()).findById(anyLong()); //called one time
    }

    @Test
    void getRecipeById() {
        Recipe recipe = Recipe.builder()
                .id(1L)
                .build();

        when(recipeRepositoryMock.findById(anyLong())).thenReturn(Optional.of(recipe));

        Recipe recipeById = recipeService.getRecipeById(1L);

        assertEquals(recipe, recipeById);
        verify(recipeRepositoryMock, times(1)).findById(anyLong());
        verify(recipeRepositoryMock, never()).findAll();
    }

    @Test
    void noRecipeFoundWhenGetRecipeById() {
        when(recipeRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class,
                () -> recipeService.getRecipeById(1L),
                "This is not th message checked");

        assertEquals("Recipe is not present", exception.getMessage());

        verify(recipeRepositoryMock, times(1)).findById(anyLong());
    }
}