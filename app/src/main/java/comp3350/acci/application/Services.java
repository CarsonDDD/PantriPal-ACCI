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
import comp3350.acci.persistence.hsqldb.ContainPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.IngredientPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.LikedPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.PantryPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.RecipePersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.SavedPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.UserPersistenceHSQLDB;
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

    private static String dbName = "PANTRIPAL";

    //Our persistence
    public static synchronized RecipePersistence getRecipePersistence() {
        if(recipePersistence == null) {
            recipePersistence = new RecipePersistenceHSQLDB(getDBPathName());
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
            ingredientPersistence = new IngredientPersistenceHSQLDB(getDBPathName());
        }
        return ingredientPersistence;
    }

    public static synchronized PantryPersistence getPantryPersistence(){
        if(pantryPersistence == null) {
            pantryPersistence = new PantryPersistenceHSQLDB(getDBPathName());
        }
        return pantryPersistence;
    }

    public static synchronized UserPersistence getUserPersistence() {
        if(userPersistence == null) {
            userPersistence = new UserPersistenceHSQLDB(getDBPathName());
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
            savedPersistence = new SavedPersistenceHSQLDB(getDBPathName());
        }
        return savedPersistence;
    }
    public static synchronized LikedPersistence getLikedPersistence() {
        if(likedPersistence == null) {
            likedPersistence = new LikedPersistenceHSQLDB(getDBPathName());
        }
        return likedPersistence;
    }

    public static synchronized ContainPersistence getContainPersistence() {
        if(containPersistence == null) {
            containPersistence = new ContainPersistenceHSQLDB(getDBPathName());
        }
        return containPersistence;
    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }


}
