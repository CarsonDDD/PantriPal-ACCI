package comp3350.acci.business;

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

    public Recipe createRecipeFrame(String authorName, String recipeName, String difficulty) {
        User author = new User(authorName, "PLACEHOLDER bio");
        Recipe newRecipe = new Recipe(author, recipeName, "Placeholder Instructions", false, difficulty);
        return recipePersistence.insertRecipe(newRecipe);
    }

    public Recipe getRecipeFromPersistence(Recipe recipe) {
        return recipePersistence.getRecipe(recipe);
    }
    //given an id, returns the associated recipe, returns null if recipe with id could not be found
    public Recipe getRecipeByID(int recipeID) {
        return recipePersistence.getRecipeByID(recipeID);
    }
    //Swaps the privacy of a recipe from false to true and true to false
    public void changePrivacy(Recipe recipe) {
        if(recipe.getIsPrivate())
            recipe.setIsPrivate(false);
        else
            recipe.setIsPrivate(true);
        recipePersistence.updateRecipe(recipe);
    }

    //sets the instructions of a given recipe
    public void setInstructions(Recipe recipe, String newInstructions) {
        recipe.setInstructions((newInstructions));
        recipePersistence.updateRecipe(recipe);
    }

    //return instructions of a recipe from persistence layer
    public String getInstructions(Recipe recipe) {
        return recipePersistence.getRecipe(recipe).getInstructions();
    }
    //returns the instructions of a recipe from the Persistence layer
    public String getInstructionsByID(int recipeID) {
        Recipe recipe = getRecipeByID(recipeID);
        return recipe.getInstructions();
    }
}
