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

        Recipe pbj = new Recipe(user1, 0, "PB&J", "Make PB&J", false, "easy");
        Recipe toast = new Recipe(user2, 1, "toast", "toast bread, spread butter", false, "easy");

        recipes.add(pbj);
        recipes.add(toast);
    }

    @Override
    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
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
