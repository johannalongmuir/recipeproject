package com.manchesterdigital.services;

import com.manchesterdigital.domain.Recipe;
import com.manchesterdigital.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class RecipeServiceImpl  implements RecipeService {

    // when autowire in the repository. Suggested use final for it
    private final RecipeRepository repository;

    public List<Recipe> getRecipes() {
        log.debug("I'm in the service");
        log.warn("This isn't a good log");
        return (List<Recipe>) repository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = repository.findById(id);

        if (!recipe.isPresent()){
            throw new RuntimeException("Recipe is not present");
        }

        return recipe.get();
    }


}
