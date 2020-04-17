package com.manchesterdigital.controllers;

import com.manchesterdigital.domain.Recipe;
import com.manchesterdigital.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    ArgumentCaptor<List<Recipe>> recipeListArgumentCapture;
    List<Recipe> recipeList;
    String viewName;


    @Test
    void mockMVC() throws Exception {
        // very much a unit test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    void when_get_index_page_return_index_view() {
        whenGetIndexPage();
        thenReturnIndexViewName();
    }

    @Test
    void when_get_index_page_methods_called_once() {
        whenGetIndexPage();
        thenGetRecipesIsCalledOnce();
        thenAddAttributeForModelIsCalledOnce();
    }

    @Test
    void when_get_index_page_parameters_for_model_are_expected() {
        givenListWithTwoRecipes();
        givenGetRecipesReturnsListWithTwoRecipes();
        whenGetIndexPage();
        whenCheckingRecipeListReturned();
        thenArgumentCaptureContainsTwoRecipes();
    }






    private void givenGetRecipesReturnsListWithTwoRecipes() {
        when(recipeServiceMock.getRecipes()).thenReturn(recipeList);
    }


    private void givenListWithTwoRecipes() {
        recipeList = new ArrayList<>();
        recipeList.add(Recipe.builder().description("Recipe One").build());
        recipeList.add(Recipe.builder().description("Recipe Two").build());

    }

    private void whenGetIndexPage() {
        viewName = indexController.getIndexPage(modelMock);
    }

    private void whenCheckingRecipeListReturned() {
        recipeListArgumentCapture = ArgumentCaptor.forClass(List.class);
        verify(modelMock, times(1)).addAttribute(any(), recipeListArgumentCapture.capture());
    }

    private void thenAddAttributeForModelIsCalledOnce() {
        verify(modelMock, times(1)).addAttribute(any(), any());
    }

    private void thenGetRecipesIsCalledOnce() {
        verify(recipeServiceMock, times(1)).getRecipes();
    }

    private void thenReturnIndexViewName() {
        assertEquals("index", viewName);
    }

    private void thenArgumentCaptureContainsTwoRecipes() {
        List<Recipe> recipesInController = recipeListArgumentCapture.getValue();
        assertEquals(2, recipesInController.size());
    }


}