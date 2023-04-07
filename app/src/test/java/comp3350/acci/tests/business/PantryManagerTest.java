package comp3350.acci.tests.business;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import comp3350.acci.business.implementation.PantryCreator;
import comp3350.acci.business.interfaces.PantryManager;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.IngredientPersistence;
import comp3350.acci.persistence.PantryPersistence;
import comp3350.acci.persistence.stubs.PantryPersistenceStub;

public class PantryManagerTest extends TestCase{

    public PantryManagerTest(String arg0){
        super(arg0);
    }

    private PantryPersistence pantMock;
    private IngredientPersistence ingMock;
    private Pantry testPantry;
    @Before
    public void setUp() {


        pantMock = Mockito.mock(PantryPersistence.class);
        ingMock = Mockito.mock(IngredientPersistence.class);

        //common mock behaviour

    }
    @After
    public void tearDown() {
        pantMock = null;
        ingMock = null;
        testPantry = null;
    }
    @Test
    public void testInsertPantry(){
        System.out.println("\nStarting pantry insertion test:");

        PantryManager manager = new PantryCreator(pantMock, ingMock);

        User user = new User("Ivory", "Professional toast chef and expert jam spreader");
        Ingredient bread = new Ingredient("Bread");

        //pantry used by the mock
        Pantry testPantry = new Pantry(user, bread, 1, "loaf");
        List<Pantry> pantryList = new ArrayList<Pantry>();
        //mock behaviour
        Mockito.when(pantMock.insertPantry(any())).thenReturn(testPantry);
        pantryList.add(testPantry);
        Mockito.when(pantMock.getPantrys()).thenReturn(pantryList);
        Mockito.when(pantMock.getPantrysByUser(any())).thenReturn(pantryList);

        Pantry pantry1 = manager.insertPantry(user, bread, 1, "loaf" );

        assertNotNull("Pantry1 should not be null", pantry1);
        assertTrue("Pantry1 was not added to the database", manager.getPantrys().contains(pantry1));
        assertTrue("Pantry1 was not added to the database with the correct user", manager.getPantrysByUser(user).contains(pantry1));

        Ingredient voidJam = new Ingredient("Void Jam");
        Pantry pantry2 = manager.insertPantry(user, voidJam, -1, "somethings");
        assertNull("Pantry2 should have returned null", pantry2);
        assertFalse("Pantry2 should not have been inserted into the database with negative amount", manager.getPantrys().contains(pantry2));

        Pantry pantry3 = manager.insertPantry(user, null, 0, "nothings");
        assertNull("Pantry3 should have returned null", pantry3);
        assertFalse("Pantry3 should not have been added to the database", manager.getPantrys().contains(pantry3));

        Ingredient sentientPB = new Ingredient("Sentient Peanut Butter");
        Pantry pantry4 = manager.insertPantry(null, sentientPB, 500, "ml");
        assertNull("Pantry3 should have returned null", pantry4);
        assertFalse("Pantry3 should not have been inserted into the database with a null user", manager.getPantrys().contains(pantry4));

        System.out.println("Pantry insertion tested successfully");
    }

    @Test
    public void testUpdatePantry(){
        System.out.println("\nStarting pantry update test:");

        PantryManager manager = new PantryCreator(pantMock, ingMock);

        User user = new User("Ivory", "Professional toast chef and expert jam spreader");

        Ingredient sugar = new Ingredient("Sugar");

        Pantry testPantry = new Pantry(user, sugar, 0.5, "cup");
        Mockito.when(pantMock.updatePantry(any())).thenReturn(testPantry);

        Pantry pantry1 = manager.insertPantry(user, sugar, 1, "cup");

        Pantry pantry2 = manager.updatePantry(user, sugar, 0.5, "cup");

        assertFalse("Pantry was not updated", pantry2.getQuantity() == 1);
        assertTrue("Pantry was not updated to the correct amount", pantry2.getQuantity() == 0.5);

        System.out.println("Pantry update tested successfully");
    }

    @Test
    public void testDeletePantry(){
        System.out.println("\nStarting pantry deletion test:");

        PantryManager manager = new PantryCreator(pantMock, ingMock);

        User user = new User("Ivory", "Professional toast chef and expert jam spreader");

        Ingredient spoiledMilk = new Ingredient("Spoiled Milk");

        Mockito.when(pantMock.insertPantry(any())).thenReturn(new Pantry(user, spoiledMilk, 1, "l"));

        Pantry pantry1 = manager.insertPantry(user, spoiledMilk, 1, "l");
        manager.deletePantry(pantry1);

        assertFalse("Item should have been removed from pantry", manager.getPantrys().contains(pantry1));

        System.out.println("Pantry deletion tested successfully");
    }

    @Test
    public void testGetPantrys(){
        System.out.println("\nStarting pantry search test:");

        PantryManager manager = new PantryCreator(pantMock, ingMock);

        User user = new User("Ivory", "Professional toast chef and expert jam spreader");

        Ingredient lemon = new Ingredient("Lemon");

        List<Pantry> pantryList = new ArrayList<Pantry>();

        Mockito.when(pantMock.insertPantry(any())).thenReturn(new Pantry(user, lemon, 2, ""));

        Pantry pantry1 = manager.insertPantry(user, lemon, 2, "");
        pantryList.add(pantry1);
        Mockito.when(pantMock.getPantrys()).thenReturn(pantryList);

        assertFalse("Pantry should not be empty", manager.getPantrys().isEmpty());

        System.out.println("\nPantry search tested successfully");
    }

    @Test
    public void testGetPantrysByUser(){
        System.out.println("\nStarting pantry search by user test:");

        PantryManager manager = new PantryCreator(pantMock, ingMock);

        User user = new User("Ivory", "Professional toast chef and expert jam spreader");

        List<Pantry> pantryList = new ArrayList<Pantry>();

        Mockito.when(pantMock.getPantrysByUser(user)).thenReturn(pantryList);

        assertTrue("List should be empty", manager.getPantrysByUser(user).isEmpty());

        Ingredient crab = new Ingredient("Crab");

        Mockito.when(pantMock.insertPantry(any())).thenReturn(new Pantry(user, crab, 1, "lb"));

        Pantry pantry1 = manager.insertPantry(user, crab, 1, "lb");
        pantryList.add(new Pantry(user, crab, 1, "lb"));

        assertTrue("Pantry1 should be in the user's pantry", manager.getPantrysByUser(user).contains(pantry1));


        assertNull("Should have returned null where no user was provided", manager.getPantrysByUser(null));

        System.out.println("Pantry search by user tested successfully");
    }
}
