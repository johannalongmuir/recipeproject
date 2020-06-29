package com.manchesterdigital.controllers;

import com.manchesterdigital.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    private final RecipeService recipeService;

    @GetMapping({"", "/", "/index"})
    public String getIndexPage (Model model){
        log.debug("In getIndexPage in Index Controller");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

    @PostMapping("/addRecipe")
    public String addRecipe (Model model){
        log.debug("In getIndexPage in Index Controller");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
