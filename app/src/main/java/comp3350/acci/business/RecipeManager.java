package comp3350.acci.business;
import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.RecipePersistence;

/*
    Name:           RecipeManager
    Description:    This is the logic layer interface for recipe DSO
                    Similar to access courses
                    Allows the presentation layer to interface
    Author/Version: Caelan Myskiw: 02/07/23

 */
public class RecipeManager {
    //needs to interface with DSO: recipe
    private RecipePersistence recipePersistence;

    public RecipeManager() {
        recipePersistence = Services.getRecipePersistence();
    }

    //creates a DSO: recipe with a given name, ingredients, and instructions. returns the recipe ID
    public Recipe createRecipe(User author, String name, String instructions, boolean isPrivate, String difficulty) {
        Recipe newRecipe = new Recipe(author, name, instructions, isPrivate, difficulty);
        newRecipe = recipePersistence.insertRecipe(newRecipe);
        return newRecipe;
    }

    //given an id, returns the associated recipe
    public Recipe getRecipeByID(int recipeID) {
        //return recipePersistence.getRecipesByID(recipeID);
        return null;
    }
    //Swaps the privacy of a recipe from false to true and true to false
    public void changePrivacy(Recipe recipe) {
        if(recipe.getIsPrivate())
            recipe.setIsPrivate(false);
        else
            recipe.setIsPrivate(true);
        recipePersistence.updateRecipe(recipe);
    }
    //Sets the ingredients for a recipe to the string array given
    //to add an ingredient use addIngredient instead
    public void setIngredients(String name, String[] ingredients) {

    }

    public void addIngredient(String name, String ingredient) {

    }

    //given a recipe name, returns the ingredient list of said recipe
    public String[] getIngredients(Recipe recipe) {
        return null;
    }

    //sets the instructions of a given recipe
    public void setInstructions(String name, String Instructions) {

    }

}
