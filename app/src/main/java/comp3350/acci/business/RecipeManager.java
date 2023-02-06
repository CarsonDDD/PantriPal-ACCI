package comp3350.acci.business;
import comp3350.acci.application.Services;
import comp3350.acci.persistence.RecipePersistence;

/*
    Name:           RecipeManager
    Description:    This is the logic layer interface for recipe DSO
                    Similar to access courses
    Author/Version: Caelan Myskiw: 02/06/23

 */
public class RecipeManager {
    //needs to interface with DSO: recipe
    private RecipePersistence recipePersistence;

    public RecipeManager() {
        recipePersistence = Services.getRecipePersistence();
    }

    //creates a DSO: recipe with a given name, ingredients, and instructions. returns the recipe ID
    public int createRecipe(String name, String instructions, boolean isPrivate, String Difficulty) {
        return -1;
    }

    //Sets the ingredients for a recipe to the string array given
    //to add an ingredient use addIngredient instead
    public void setIngredients(String name, String[] ingredients) {

    }

    public void addIngredient(String name, String ingredient) {

    }

    //given a recipe name, returns the ingredient list of said recipe
    public String[] getIngredients(String name) {
        return null;
    }

    //sets the instructions of a given recipe
    public void setInstructions(String name, String Instructions) {

    }

}
