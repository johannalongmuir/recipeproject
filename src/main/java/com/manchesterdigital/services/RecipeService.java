package com.manchesterdigital.services;

import com.manchesterdigital.domain.Recipe;

import java.util.List;

public interface RecipeService  {

   List<Recipe> getRecipes();

   Recipe getRecipeById(Long id);

}
