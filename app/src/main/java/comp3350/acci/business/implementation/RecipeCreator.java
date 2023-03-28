package comp3350.acci.business.implementation;

import java.security.InvalidParameterException;
import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.RecipePersistence;
import comp3350.acci.persistence.ContainPersistence;

/*
    Name:           RecipeManager
    Description:    This is the logic layer interface for recipe DSO
                    Similar to access courses
                    Allows the presentation layer to interface
    Author/Version: Caelan Myskiw: 02/07/23

 */
public class RecipeCreator implements comp3350.acci.business.interfaces.RecipeManager {
    //needs to interface with DSO: recipe
    private RecipePersistence recipePersistence;
    private ContainPersistence containPersistence;

    public RecipeCreator(RecipePersistence rp, ContainPersistence cp) {
        recipePersistence = rp;
        containPersistence = cp;

    }

    //creates a DSO: recipe with a given name, ingredients, and instructions. returns the recipe ID
    public Recipe createRecipe(User author, String name, String instructions, boolean isPrivate, String difficulty) {
        if(author == null|| name.equals("") || instructions.equals("") ||difficulty.equals("") ) {
            return null;
        }

        Recipe newRecipe = recipePersistence.insertRecipe(new Recipe(author, name, instructions, isPrivate, difficulty));
        return newRecipe;
    }

    @Override
    public void modify(Recipe output, String name, String instructions, boolean isPrivate, String difficulty) throws InvalidParameterException{
        if(name.equals("") || instructions.equals("") ||difficulty.equals("") ) {
            throw new InvalidParameterException("one or more properties are empty");
        }

        output.setName(name);
        output.setInstructions(instructions);
        output.setIsPrivate(isPrivate);
        output.setDifficulty(difficulty);
        recipePersistence.updateRecipe(output);
    }

    public List<Recipe> getRecipes() {
        return recipePersistence.getRecipes();
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
    public List<Recipe> getUsersRecipes(User author) {
        return recipePersistence.getUserRecipes(author);
    }

    public Contain insertContain(Recipe recipe, Ingredient ingredient, double amount, String unit){
        Contain result = null;

        if(recipe != null && ingredient != null && amount > 0){
            result = containPersistence.insertContain(new Contain(recipe, ingredient, amount, unit));
        }
        return result;
    }

    public void deleteContain(Contain contain){
        if(contain != null){
            containPersistence.deleteContain(contain);
        }
    }

    public List<Contain> getContainByRecipe(Recipe recipe){
        List<Contain> result = null;
        if(recipe != null){
            result = containPersistence.getContainsByRecipe(recipe);
        }
        return result;
    }

    public List<Contain> getContain(){
        return containPersistence.getContains();
    }
}
