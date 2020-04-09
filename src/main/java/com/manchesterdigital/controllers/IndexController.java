package com.manchesterdigital.controllers;

import com.manchesterdigital.services.RecipeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class IndexController {

    private final RecipeServiceImpl recipeService;

    @GetMapping({"", "/", "/index"})
    public String getIndexPage (Model model){
        model.addAttribute("recipes", recipeService.getRecipes());


        return "index";
    }
}
