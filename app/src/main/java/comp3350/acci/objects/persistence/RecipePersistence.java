package comp3350.acci.objects.persistence;

import java.util.List;

import comp3350.acci.objects.Recipe;

public interface RecipePersistence {
    List<Recipe> getRecipes();

    Recipe getRecipeByID(int id);

    Recipe getRecipe(Recipe recipe);

    Recipe insertRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);
}
