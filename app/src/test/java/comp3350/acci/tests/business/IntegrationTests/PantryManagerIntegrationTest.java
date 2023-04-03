package comp3350.acci.tests.business.IntegrationTests;

import static org.mockito.ArgumentMatchers.any;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comp3350.acci.business.implementation.PantryCreator;
import comp3350.acci.business.interfaces.PantryManager;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.IngredientPersistence;
import comp3350.acci.persistence.PantryPersistence;
import comp3350.acci.persistence.UserPersistence;
import comp3350.acci.persistence.hsqldb.IngredientPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.PantryPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.acci.tests.utils.TestFilesUtil;

public class PantryManagerIntegrationTest extends TestCase{

    public PantryManagerIntegrationTest(String arg0){
        super(arg0);
    }
    private PantryPersistence pantPers;
    private Pantry testPantry;
    private File tempDB;
    private User testUser;

    private Ingredient testIngredient;
    @Before
    public void setUp() throws IOException {
        this.tempDB = TestFilesUtil.copyDB();
        pantPers = new PantryPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));

        UserPersistence tempUsePers = new UserPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        IngredientPersistence tempIngPers = new IngredientPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        testUser = tempUsePers.getCurrentUser();
        testIngredient = tempIngPers.getIngredients().get(0);
    }
    @After
    public void tearDown() {
        pantPers = null;
        testPantry = null;
        testUser = null;
    }
    @Test
    public void testInsertPantry(){
        System.out.println("\nStarting pantry insertion test:");

        PantryManager manager = new PantryCreator(pantPers);

        



        Pantry pantry1 = manager.insertPantry(testUser, testIngredient, 1, "loaf" );

        assertNotNull("Pantry1 should not be null", pantry1);
        assertTrue("Pantry1 was not added to the database", manager.getPantrys().contains(pantry1));
        assertTrue("Pantry1 was not added to the database with the correct user", manager.getPantrysByUser(testUser).contains(pantry1));



        Pantry pantry2 = manager.insertPantry(testUser, testIngredient, -1, "somethings");
        assertNull("Pantry2 should have returned null", pantry2);
        assertFalse("Pantry2 should not have been inserted into the database with negative amount", manager.getPantrys().contains(pantry2));

        Pantry pantry3 = manager.insertPantry(testUser, null, 0, "nothings");
        assertNull("Pantry3 should have returned null", pantry3);
        assertFalse("Pantry3 should not have been added to the database", manager.getPantrys().contains(pantry3));


        Pantry pantry4 = manager.insertPantry(null, testIngredient, 500, "ml");
        assertNull("Pantry3 should have returned null", pantry4);
        assertFalse("Pantry3 should not have been inserted into the database with a null user", manager.getPantrys().contains(pantry4));

        System.out.println("Pantry insertion tested successfully");
    }

    @Test
    public void testUpdatePantry(){
        System.out.println("\nStarting pantry update test:");

        PantryManager manager = new PantryCreator(pantPers);

        

        Pantry testPantry = new Pantry(testUser, testIngredient, 0.5, "cup");


        Pantry pantry1 = manager.insertPantry(testUser, testIngredient, 1, "cup");

        Pantry pantry2 = manager.updatePantry(testUser, testIngredient, 0.5, "cup");

        assertFalse("Pantry was not updated", pantry2.getQuantity() == 1);
        assertTrue("Pantry was not updated to the correct amount", pantry2.getQuantity() == 0.5);

        System.out.println("Pantry update tested successfully");
    }

    @Test
    public void testDeletePantry(){
        System.out.println("\nStarting pantry deletion test:");

        PantryManager manager = new PantryCreator(pantPers);

        Pantry pantry1 = manager.insertPantry(testUser, testIngredient, 1, "l");
        manager.deletePantry(pantry1);

        assertFalse("Item should have been removed from pantry", manager.getPantrys().contains(pantry1));

        System.out.println("Pantry deletion tested successfully");
    }

    @Test
    public void testGetPantrys(){
        System.out.println("\nStarting pantry search test:");

        PantryManager manager = new PantryCreator(pantPers);

        assertFalse("Pantry should not be empty", manager.getPantrys().isEmpty());

        System.out.println("\nPantry search tested successfully");
    }

    @Test
    public void testGetPantrysByUser(){
        System.out.println("\nStarting pantry search by user test:");

        PantryManager manager = new PantryCreator(pantPers);






        assertTrue("List should be empty", manager.getPantrysByUser(testUser).isEmpty());




        Pantry pantry1 = manager.insertPantry(testUser, testIngredient, 1, "lb");

        assertTrue("Pantry1 should be in the user's pantry", manager.getPantrysByUser(testUser).contains(pantry1));


        assertNull("Should have returned null where no user was provided", manager.getPantrysByUser(null));

        System.out.println("Pantry search by user tested successfully");
    }
}
