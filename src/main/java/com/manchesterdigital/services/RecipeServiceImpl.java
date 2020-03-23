package com.manchesterdigital.services;

import com.manchesterdigital.domain.Recipe;
import com.manchesterdigital.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecipeServiceImpl  implements RecipeService {

    // autowire in the repository
    RecipeRepository repository;

    public RecipeServiceImpl(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> getRecipes() {
        return (List<Recipe>) repository.findAll();
    }

}
