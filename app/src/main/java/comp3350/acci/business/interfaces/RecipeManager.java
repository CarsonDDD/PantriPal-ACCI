package comp3350.acci.business.interfaces;

import java.util.List;

import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public interface RecipeManager {
    public Recipe createRecipe(User author, String name, String instructions, boolean isPrivate, String difficulty);
    public void modify(Recipe output, String name, String instructions, boolean isPrivate, String difficulty);
    public List<Recipe> getRecipes();
    public List<Recipe> getUserAndPublicRecipes(User user);
    public List<Recipe> getPublicUserRecipes(User user);
    public Recipe getRecipeFromPersistence(Recipe recipe);
    public Recipe getRecipeByID(int recipeID);
    public void changePrivacy(Recipe recipe);

    public void setInstructions(Recipe recipe, String newInstructions);
    public String getInstructions(Recipe recipe);
    public String getInstructionsByID(int recipeID);

    List<Recipe> getUsersRecipes(User author);

    Contain insertContain(Recipe recipe, Ingredient ingredient, double amount, String unit);
    void deleteContain(Contain contain);
    List<Contain> getContainByRecipe(Recipe recipe);
    List<Contain> getContain();
}
