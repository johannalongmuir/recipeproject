package com.manchesterdigital.controllers;

import com.manchesterdigital.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @RequestMapping({"/recipe/show/{id}"})
    public String displayById(Model model, @PathVariable String id){
        log.debug("In displayById in Index Controller");
        model.addAttribute("recipe", recipeService.getRecipeById(Long.parseLong(id)));
        return "recipe/show";
    }

}
