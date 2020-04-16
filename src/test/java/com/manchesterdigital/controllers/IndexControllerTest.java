package com.manchesterdigital.controllers;

import com.manchesterdigital.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    /*
    Junit test for IndexController
    Use Mockito Mock for RecipeService and Model
    Verify proper string is returned
    Verify interactions with two mocks.
    eq("recipes") instead of any() specifies that first argument equals
     */

    RecipeService recipeServiceMock = mock(RecipeService.class);
    Model modelMock = mock(Model.class);
    IndexController indexController = new IndexController(recipeServiceMock);


    @Test
    void getIndexPage() {
        String viewName = indexController.getIndexPage(modelMock);
        assertEquals("index", viewName);
        verify(recipeServiceMock, times(1)).getRecipes();
        verify(modelMock, times(1)).addAttribute(any(), any());
    }
}