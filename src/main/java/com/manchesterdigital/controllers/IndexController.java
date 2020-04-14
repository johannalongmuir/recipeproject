package com.manchesterdigital.controllers;

import com.manchesterdigital.services.RecipeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    private final RecipeServiceImpl recipeService;

    @GetMapping({"", "/", "/index"})
    public String getIndexPage (Model model){
        log.debug("In getIndexPage in Index Controller");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
