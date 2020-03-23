package com.manchesterdigital.bootstrap;

import com.manchesterdigital.domain.*;
import com.manchesterdigital.repositories.CategoryRepository;
import com.manchesterdigital.repositories.RecipeRepository;
import com.manchesterdigital.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    UnitOfMeasure teaspoon;
    UnitOfMeasure tablespoon;
    UnitOfMeasure cup;
    UnitOfMeasure pinch;
    UnitOfMeasure ounce;
    UnitOfMeasure gram;
    UnitOfMeasure kilogram;
    UnitOfMeasure whole;
    UnitOfMeasure garnish;
    UnitOfMeasure millilitres;

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        getUnitOfMeasureAmounts();
        addPancakeRecipeToRecipeRepository();
        addPerfectGuacamoleToRecipeRepository();
        addSpicyGrilledChickenTacoToRecipeRepository();
    }

    private void addPancakeRecipeToRecipeRepository() {
        Recipe pancakes = new Recipe();
        pancakes.setDescription("Pancakes");
        pancakes.setPrepTime(10);
        pancakes.setCookTime(20);
        pancakes.setServings(4);
        pancakes.setSource("Emma Dudley");
        pancakes.setUrl("Not applicable");
        pancakes.setDirections("1 Put all dry ingredients into a mixing bowl. \n" +
                "2 Melt the butter. \n" +
                "3 Add to the dry ingredients. \n" +
                "4 Crack eggs and stir into the mix. \n" +
                "5 Add the milk. \n" +
                "6 Ensure mixture thoroughly combined. \n" +
                "7 Turn on stove to medium heat. \n" +
                "8 Add a knob of buttterrrr. \n" +
                "9 Ladle in one serving to the pan. \n" +
                "10 Wait for bubbles to form in the mixture as it cooks. \n" +
                "11 Flip the pancake. \n" +
                "12 Cook for 2 minutes. \n" +
                "13 Add to plate. \n" +
                "14 Repeat until out of mixture. \n" +
                "15 Add desired toppings. \n" +
                "16 EAT THE BITCH!!!. \n");

        pancakes.setDifficulty(Difficulty.EASY);
        pancakes.setIngredients(new HashSet<>());
        pancakes.getIngredients().add(new Ingredient("flour", BigDecimal.valueOf(2), cup, pancakes));
        pancakes.getIngredients().add(new Ingredient("baking powder", BigDecimal.valueOf(2), teaspoon, pancakes));
        pancakes.getIngredients().add(new Ingredient("sugar", BigDecimal.valueOf(1), tablespoon, pancakes));
        pancakes.getIngredients().add(new Ingredient("salt", BigDecimal.ONE, pinch, pancakes));
        pancakes.getIngredients().add(new Ingredient("eggs", BigDecimal.valueOf(2), whole, pancakes));
        pancakes.getIngredients().add(new Ingredient("milk", BigDecimal.valueOf(200), millilitres, pancakes));
        pancakes.getIngredients().add(new Ingredient("butter", BigDecimal.valueOf(2), tablespoon, pancakes));


        pancakes.setNotes(new Notes());
        pancakes.setCategories(new HashSet<>());
        Optional<Category> optionalBreakfast = categoryRepository.findByDescription("Breakfast");
        pancakes.getCategories().add(optionalBreakfast.get());

        recipeRepository.save(pancakes);

    }

    private void getUnitOfMeasureAmounts() {
        Optional<UnitOfMeasure> optionalTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> optionalTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> optionalCup = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> optionalPinch = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> optionalOunce = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> optionalGram = unitOfMeasureRepository.findByDescription("Gram");
        Optional<UnitOfMeasure> optionalKg = unitOfMeasureRepository.findByDescription("Kilogram");
        Optional<UnitOfMeasure> optionalWhole = unitOfMeasureRepository.findByDescription("Whole");
        Optional<UnitOfMeasure> optionalGarnish = unitOfMeasureRepository.findByDescription("Garnish");
        Optional<UnitOfMeasure> optionalMillilitres = unitOfMeasureRepository.findByDescription("Millilitres");

        if (optionalTeaspoon.isEmpty() || optionalTablespoon.isEmpty() || optionalCup.isEmpty()
                || optionalPinch.isEmpty() || optionalOunce.isEmpty() || optionalGram.isEmpty()
                || optionalKg.isEmpty() || optionalWhole.isEmpty() || optionalGarnish.isEmpty()
                || optionalMillilitres.isEmpty()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        teaspoon = optionalTeaspoon.get();
        tablespoon = optionalTablespoon.get();
        cup = optionalCup.get();
        pinch = optionalPinch.get();
        ounce = optionalOunce.get();
        gram = optionalGram.get();
        kilogram = optionalKg.get();
        whole = optionalWhole.get();
        garnish = optionalGarnish.get();
        millilitres = optionalMillilitres.get();
    }

    private void addSpicyGrilledChickenTacoToRecipeRepository() {
        Recipe spicyGrilledChickenTaco = new Recipe();
        spicyGrilledChickenTaco.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTaco.setPrepTime(20);
        spicyGrilledChickenTaco.setCookTime(15);
        spicyGrilledChickenTaco.setServings(4);
        spicyGrilledChickenTaco.setSource("Simply Recipes");
        spicyGrilledChickenTaco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilledChickenTaco.setDirections(
                "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");


        spicyGrilledChickenTaco.setDifficulty(Difficulty.EASY);
        spicyGrilledChickenTaco.setIngredients(new HashSet<>());

        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("ancho chili powder", BigDecimal.valueOf(2.0), tablespoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("dried oregano", BigDecimal.valueOf(1.0), teaspoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("dried cumin", BigDecimal.valueOf(1.0), teaspoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("sugar", BigDecimal.valueOf(1.0), teaspoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("salt", BigDecimal.valueOf(1/2), teaspoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("garlic clove, finely chopped", BigDecimal.valueOf(1.0), whole, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("finely grated orange zest", BigDecimal.valueOf(1.0), tablespoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3.0), tablespoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("olive oil", BigDecimal.valueOf(2.0), tablespoon, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("boneless chicken thighs", BigDecimal.valueOf(4.0), whole, spicyGrilledChickenTaco));

        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("small corn tortillas", BigDecimal.valueOf(8.0), whole, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("baby arugula", BigDecimal.valueOf(3.0), cup, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("medium ripe avocado", BigDecimal.valueOf(2.0), whole, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("radishes, thinly sliced", BigDecimal.valueOf(4.0), whole, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("cherry tomatoes, halved", BigDecimal.valueOf(1.0), cup, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("red onion, thinly sliced", BigDecimal.valueOf(1/4), whole, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("roughly chopped cilantro", BigDecimal.valueOf(1.0), garnish, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("sour cream thinned with milk", BigDecimal.valueOf(1/2), cup, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("milk to thin sour cream", BigDecimal.valueOf(1/4), cup, spicyGrilledChickenTaco));
        spicyGrilledChickenTaco.getIngredients().add(new Ingredient("lime, cut in wedges", BigDecimal.valueOf(1.0), whole, spicyGrilledChickenTaco));

        // TODO work out how to set image

        spicyGrilledChickenTaco.setNotes(new Notes());

        spicyGrilledChickenTaco.setCategories(new HashSet<>());

        Optional<Category> optionalMexican = categoryRepository.findByDescription("Mexican");
        if (optionalMexican.isEmpty()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        spicyGrilledChickenTaco.getCategories().add(optionalMexican.get());

        recipeRepository.save(spicyGrilledChickenTaco);
    }

    private void addPerfectGuacamoleToRecipeRepository() {
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setServings(2);
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        perfectGuacamole.setDifficulty(Difficulty.EASY);

        perfectGuacamole.setIngredients(new HashSet<>());


        perfectGuacamole.getIngredients().add(new Ingredient("Ripe avocado", BigDecimal.valueOf(2.0), whole, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("Salt", BigDecimal.valueOf(1/4), teaspoon, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("Fresh lime or lemon juice", BigDecimal.valueOf(1.0), tablespoon, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("Minced red onion or thinly sliced green onion", BigDecimal.valueOf(2.0), tablespoon, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(1.0), whole, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2.0), tablespoon, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("freshly grated black pepper", BigDecimal.valueOf(1.0), pinch, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(0.5), whole, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("Red radishes or jicama", BigDecimal.valueOf(1.0), garnish, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("Tortilla chips", BigDecimal.valueOf(1.0), garnish, perfectGuacamole));


        // TODO work out how to set image
        // perfectGuacamole.setImage("https://www.simplyrecipes.com/wp-content/uploads/2018/07/Guacamole-LEAD-1.jpg");

        perfectGuacamole.setNotes(new Notes());
        perfectGuacamole.setCategories(new HashSet<>());

        Optional<Category> optionalMexican = categoryRepository.findByDescription("Mexican");
        Optional<Category> optionalSide = categoryRepository.findByDescription("Side");

        if (optionalMexican.isEmpty() || optionalSide.isEmpty()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        perfectGuacamole.getCategories().add(optionalMexican.get());
        perfectGuacamole.getCategories().add(optionalSide.get());

        recipeRepository.save(perfectGuacamole);
    }

}
