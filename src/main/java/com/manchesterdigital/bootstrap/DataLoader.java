package com.manchesterdigital.bootstrap;

import com.manchesterdigital.domain.*;
import com.manchesterdigital.repositories.CategoryRepository;
import com.manchesterdigital.repositories.RecipeRepository;
import com.manchesterdigital.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        pancakes.addIngredient(new Ingredient("flour", new BigDecimal(2), cup));
        pancakes.addIngredient(new Ingredient("baking powder", new BigDecimal(2), teaspoon));
        pancakes.addIngredient(new Ingredient("sugar", new BigDecimal(1), tablespoon));
        pancakes.addIngredient(new Ingredient("salt", new BigDecimal(1), pinch));
        pancakes.addIngredient(new Ingredient("eggs", new BigDecimal(2), whole));
        pancakes.addIngredient(new Ingredient("milk", new BigDecimal(200), millilitres));
        pancakes.addIngredient(new Ingredient("butter", new BigDecimal(2), tablespoon));



        Notes notes = new Notes();
        notes.setRecipeNotes("Emmas pancakes are the best");
        notes.setRecipe(pancakes);
        pancakes.setNotes(notes);
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

        spicyGrilledChickenTaco.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("salt", new BigDecimal("0.5"), teaspoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("garlic clove, finely chopped", new BigDecimal(1), whole));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoon));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), whole));

        spicyGrilledChickenTaco.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), whole));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("baby arugula", new BigDecimal(3), cup));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("medium ripe avocado", new BigDecimal(2), whole));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), whole));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(1), cup));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal("0.25"), whole));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("roughly chopped cilantro", new BigDecimal(1), garnish));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("sour cream thinned with milk", new BigDecimal("0.5"), cup));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("milk to thin sour cream", new BigDecimal("0.25"), cup));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("lime, cut in wedges", new BigDecimal(1), whole));

        // TODO work out how to set image

        Notes notes = new Notes();
        notes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        notes.setRecipe(spicyGrilledChickenTaco);
        spicyGrilledChickenTaco.setNotes(notes);



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


        perfectGuacamole.addIngredient(new Ingredient("Ripe avocado", new BigDecimal(2), whole));
        perfectGuacamole.addIngredient(new Ingredient("Salt", new BigDecimal(0.25), teaspoon));
        perfectGuacamole.addIngredient(new Ingredient("Fresh lime or lemon juice", new BigDecimal(1), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("Minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(1), whole));
        perfectGuacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), pinch));
        perfectGuacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), whole));
        perfectGuacamole.addIngredient(new Ingredient("Red radishes or jicama", new BigDecimal(1), garnish));
        perfectGuacamole.addIngredient(new Ingredient("Tortilla chips", new BigDecimal(1), garnish));


        // TODO work out how to set image
        // perfectGuacamole.setImage("https://www.simplyrecipes.com/wp-content/uploads/2018/07/Guacamole-LEAD-1.jpg");



        Notes notes = new Notes();
        notes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\\n\" +\n" +
                "                \"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\\n\" +\n" +
                "                \"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\\n\" +\n" +
                "                \"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws\"");
        notes.setRecipe(perfectGuacamole);
        perfectGuacamole.setNotes(notes);

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
