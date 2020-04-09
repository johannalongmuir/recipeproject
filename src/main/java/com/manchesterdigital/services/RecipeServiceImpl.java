package com.manchesterdigital.services;

import com.manchesterdigital.domain.Recipe;
import com.manchesterdigital.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class RecipeServiceImpl  implements RecipeService {

    // autowire in the repository. Suggested use final for it
    private final RecipeRepository repository;

    public List<Recipe> getRecipes() {
        log.debug("I'm in the service");
        return (List<Recipe>) repository.findAll();
    }

}
