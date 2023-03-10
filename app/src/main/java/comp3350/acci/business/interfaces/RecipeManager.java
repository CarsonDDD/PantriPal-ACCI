package comp3350.acci.business.interfaces;

import java.util.List;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public interface RecipeManager {
    public Recipe createRecipe(User author, String name, String instructions, boolean isPrivate, String difficulty);
    public List<Recipe> getRecipes();
    public Recipe getRecipeFromPersistence(Recipe recipe);
    public Recipe getRecipeByID(int recipeID);
    public void changePrivacy(Recipe recipe);

    public void setInstructions(Recipe recipe, String newInstructions);
    public String getInstructions(Recipe recipe);
    public String getInstructionsByID(int recipeID);

    List<Recipe> getUsersRecipes(User author);
}
