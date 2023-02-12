package comp3350.acci.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.RecipePersistence;

public class RecipePersistenceStub implements RecipePersistence {

    private List<Recipe> recipes;

    public RecipePersistenceStub() {
        this.recipes = new ArrayList<>();

        User user1 = new User(0, "600cows", "hi im ayden");
        User user2 = new User(1, "testuser", "bio");

        String pbjString = "1. Spread the peanut butter on one piece of bread.\n" +
                "2. Spread the jelly on the other side.\n" +
                "3. Put the two pieces of bread together to form a sandwich.\n" +
                "Toddler adaptation: cut off crusts before serving.";

        String grilledCheeseString = "1. Butter the bread on one side and place the bread butter-side down on a hot skillet.\n" +
                "2. Top with cheese, then place another slice of bread on top (butter-side up).\n" +
                "3. Cook until the bottom slice is lightly browned, then flip.\n" +
                "4. Continue cooking until the cheese is melted.";

        Recipe pbj = new Recipe(user1, 0, "Peanut Butter and Jelly", pbjString, false, "easy");
        Recipe toast = new Recipe(user2, 1, "Grilled Cheese", grilledCheeseString, false, "easy");

        recipes.add(pbj);
        recipes.add(toast);
    }

    @Override
    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }
    @Override
    public Recipe getRecipe(Recipe recipe) {
        for (int i = 0; i<recipes.size(); i++){
            if (recipes.get(i).equals(recipe)) {
                return recipes.get(i);
            }
        }
        return null;
    }
    @Override
    public Recipe getRecipeByID(int id) {
        for (int i = 0; i<recipes.size(); i++){
            if (recipes.get(i).getRecipeID() == id) {
                return recipes.get(i);
            }
        }
        return null;
    }

    @Override
    public Recipe insertRecipe(Recipe recipe) {
        recipes.add(recipe);
        return recipe;
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        int index;

        index = recipes.indexOf(recipe);
        if (index >= 0)
        {
            recipes.set(index, recipe);
        }
        return recipe;
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        int index;

        index = recipes.indexOf(recipe);
        if (index >= 0)
        {
            recipes.remove(index);
        }
    }
}
