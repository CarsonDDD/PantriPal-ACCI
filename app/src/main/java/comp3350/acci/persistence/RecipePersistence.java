package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public interface RecipePersistence {
    List<Recipe> getRecipes();

    Recipe getRecipeByID(int id);

    Recipe getRecipe(Recipe recipe);

    List<Recipe> getUserRecipes(User user);

    Recipe insertRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);
}
