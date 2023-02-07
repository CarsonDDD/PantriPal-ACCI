package comp3350.acci.application;

import java.util.List;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Recipe;
import comp3350.acci.persistence.ContainPersistence;
import comp3350.acci.persistence.CoursePersistence;
import comp3350.acci.persistence.IngredientPersistence;
import comp3350.acci.persistence.LikedPersistence;
import comp3350.acci.persistence.PantryPersistence;
import comp3350.acci.persistence.RecipePersistence;
import comp3350.acci.persistence.SCPersistence;
import comp3350.acci.persistence.SavedPersistence;
import comp3350.acci.persistence.StudentPersistence;
import comp3350.acci.persistence.UserPersistence;
import comp3350.acci.persistence.stubs.ContainPersistenceStub;
import comp3350.acci.persistence.stubs.CoursePersistenceStub;
import comp3350.acci.persistence.stubs.IngredientPersistenceStub;
import comp3350.acci.persistence.stubs.LikedPersistenceStub;
import comp3350.acci.persistence.stubs.PantryPersistenceStub;
import comp3350.acci.persistence.stubs.RecipePersistenceStub;
import comp3350.acci.persistence.stubs.SCPersistenceStub;
import comp3350.acci.persistence.stubs.SavedPersistenceStub;
import comp3350.acci.persistence.stubs.StudentPersistenceStub;
import comp3350.acci.persistence.stubs.UserPersistenceStub;

public class Services
{
    //stuff to remove
	private static StudentPersistence studentPersistence = null;
	private static CoursePersistence coursePersistence = null;
	private static SCPersistence scPersistence = null;

    //Our stuff
    private static RecipePersistence recipePersistence = null;
    private static PantryPersistence pantryPersistence = null;
    private static IngredientPersistence ingredientPersistence = null;
    private static UserPersistence userPersistence = null;

    private static SavedPersistence savedPersistence = null;

    private static LikedPersistence likedPersistence = null;

    private static ContainPersistence containPersistence = null;

    //Stuff to remove:
	public static synchronized StudentPersistence getStudentPersistence()
    {
		if (studentPersistence == null)
		{
		    studentPersistence = new StudentPersistenceStub();
        }

        return studentPersistence;
	}
    public static synchronized CoursePersistence getCoursePersistence() {
        if (coursePersistence == null) {
            coursePersistence = new CoursePersistenceStub();
        }

        return coursePersistence;
    }
	public static synchronized SCPersistence getScPersistence() {
        if (scPersistence == null) {
            scPersistence = new SCPersistenceStub();
        }

        return scPersistence;
    }

    //Our persistence
    public static synchronized RecipePersistence getRecipePersistence() {
        if(recipePersistence == null) {
            recipePersistence = new RecipePersistenceStub();
        }
        return recipePersistence;
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
