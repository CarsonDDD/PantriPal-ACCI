package comp3350.acci.application;

import comp3350.acci.business.implementation.RecipeCreator;
import comp3350.acci.business.implementation.UserCreator;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.persistence.ContainPersistence;

import comp3350.acci.persistence.IngredientPersistence;
import comp3350.acci.persistence.LikedPersistence;
import comp3350.acci.persistence.PantryPersistence;
import comp3350.acci.persistence.RecipePersistence;

import comp3350.acci.persistence.SavedPersistence;

import comp3350.acci.persistence.UserPersistence;
import comp3350.acci.persistence.stubs.ContainPersistenceStub;
import comp3350.acci.persistence.stubs.IngredientPersistenceStub;
import comp3350.acci.persistence.stubs.LikedPersistenceStub;
import comp3350.acci.persistence.stubs.PantryPersistenceStub;
import comp3350.acci.persistence.stubs.RecipePersistenceStub;
import comp3350.acci.persistence.stubs.SavedPersistenceStub;
import comp3350.acci.persistence.stubs.UserPersistenceStub;

public class Services
{

    private static RecipePersistence recipePersistence = null;
    private static RecipeManager recipeManager = null;
    private static PantryPersistence pantryPersistence = null;
    private static IngredientPersistence ingredientPersistence = null;
    private static UserPersistence userPersistence = null;
    private static UserManager userManager = null;

    private static SavedPersistence savedPersistence = null;

    private static LikedPersistence likedPersistence = null;

    private static ContainPersistence containPersistence = null;

    //Our persistence
    public static synchronized RecipePersistence getRecipePersistence() {
        if(recipePersistence == null) {
            recipePersistence = new RecipePersistenceStub();
        }
        return recipePersistence;
    }
    public static synchronized RecipeManager getRecipeManager() {
        if(recipeManager == null) {
            recipeManager = new RecipeCreator();
        }
        return recipeManager;
    }
    public static synchronized IngredientPersistence getIngredientPersistence() {
        if(ingredientPersistence == null) {
            ingredientPersistence = new IngredientPersistenceStub();
        }
        return ingredientPersistence;
    }

    public static synchronized PantryPersistence getPantryPersistence(){
        if(pantryPersistence == null) {
            pantryPersistence = new PantryPersistenceStub();
        }
        return pantryPersistence;
    }

    public static synchronized UserPersistence getUserPersistence() {
        if(userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }
    public static synchronized UserManager getUserManager() {
        if(userManager == null) {
            userManager = new UserCreator();
        }
        return userManager;
    }
    public static synchronized SavedPersistence getSavedPersistence() {
        if(savedPersistence == null) {
            savedPersistence = new SavedPersistenceStub();
        }
        return savedPersistence;
    }
    public static synchronized LikedPersistence getLikedPersistence() {
        if(likedPersistence == null) {
            likedPersistence = new LikedPersistenceStub();
        }
        return likedPersistence;
    }

    public static synchronized ContainPersistence getContainPersistence() {
        if(containPersistence == null) {
            containPersistence = new ContainPersistenceStub();
        }
        return containPersistence;
    }
}
