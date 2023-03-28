
package comp3350.acci.tests.business;

//java imports


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//project imports
import comp3350.acci.business.implementation.RecipeCreator;
import comp3350.acci.business.interfaces.RecipeManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.ContainPersistence;
import comp3350.acci.persistence.RecipePersistence;


public class RecipeManagerTest extends TestCase {
    @Mock
    private RecipePersistence repMock;
    @Mock
    private ContainPersistence contMock;

    private RecipeManager manager;

    private User testAuthor;
    private Recipe testRep;
    public RecipeManagerTest(String arg0 ) {
        super(arg0);

    }
    @Before
    public void setUp() {
        repMock = Mockito.mock(RecipePersistence.class);
        contMock = Mockito.mock(ContainPersistence.class);
        manager = new RecipeCreator(repMock, contMock);
        testAuthor = new User("Caelan", "I'm the coolest");
        testRep = new Recipe(testAuthor, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");
        //define mock behaviour
        when(repMock.insertRecipe(any())).thenReturn(testRep);
    }
    @After
    public void tearDown() {
        repMock = null;
        manager = null;
        testAuthor = null;
        testRep = null;
    }
    @Test
    public void testCreateRecipe() {
        System.out.println("\nStarting recipe creation test");

        //define mock behaviour
        when(repMock.getRecipe(testRep)).thenReturn(testRep);

        Recipe rep1 = manager.createRecipe(testAuthor, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");
        Recipe manRep = manager.getRecipeFromPersistence(rep1);

        Recipe rep2 = manager.createRecipe(null, "", "", false, "");
        assertNull(rep2);
        assertNotNull("Recipe manager should not have given back a null recipe",rep1);
        assertNotNull("Recipe manager should successfully give back a non-null recipe from the persistence",manRep);

        assertEquals("The recipe given back should have the same author",testAuthor, manRep.getAuthor());

        assertEquals("recipe name should not have been changed by manager","PB&J", rep1.getName());
        assertEquals("recipe name should not have been changed by persistence","PB&J", manRep.getName());

        assertEquals("Instructions should not have been changed by manager","Put peanut butter and jam on toast.", rep1.getInstructions());
        assertEquals("Instructions should not have been changed by persistence","Put peanut butter and jam on toast.", manRep.getInstructions());

        assertFalse("Recipe privacy should not have been changed by manager",rep1.getIsPrivate());
        assertFalse("Recipe privacy should not have been changed by persistence",manRep.getIsPrivate());
        System.out.println("Created Recipe had all expected fields");
    }
    @Test
    public void testRecipePrivacy() {
        System.out.println("\nStarting Recipe privacy Test:");

        Recipe rep1 = manager.createRecipe(testAuthor, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");

        assertFalse("Recipe should be public (unchanged)",rep1.getIsPrivate());

        manager.changePrivacy(rep1);

        assertTrue("Recipe privacy should be changed to true",rep1.getIsPrivate());

        manager.changePrivacy(rep1);

        assertFalse("Recipe should be changed to false",rep1.getIsPrivate());

        System.out.println("changePrivacy tested successfully");
    }
    @Test
    public void testRecipeInstructions() {
        System.out.println("\nStarting recipe instruction Tests:");

        //set mock behaviour
        when(repMock.getRecipe(testRep)).thenReturn(testRep);
        when(repMock.updateRecipe(testRep)).thenReturn(testRep);

        assertNotNull("Instructions field should be non-null",testRep.getInstructions());
        assertNotNull("Getter from manager should produce non-null",manager.getInstructions(testRep));

        assertEquals("recipe from persistence and from recipe should be equal",testRep.getInstructions(), manager.getInstructions(testRep));

        String newInstructions = "Put the jam and peanut butter on some bread";


        manager.setInstructions(testRep, newInstructions);

        assertEquals("Recipe should have new instructions in persistence",newInstructions, manager.getInstructions(testRep));

        System.out.println("Recipe Instructions tested Successfully");
    }


    @Test
    public void testModify() {
        System.out.println("\nStarting recipe modify tests: ");
        Recipe rep1 = manager.createRecipe(testAuthor, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");

        //test normal behaviour
        String newName = "TestName";
        String newInstructions = "New instructions";
        String newDifficulty = "Easy";
        manager.modify(rep1, newName, newInstructions, true, newDifficulty);

        assertNotNull("Recipe should not be null after being modified", rep1);
        assertEquals("Recipe should have the new name", rep1.getName(), newName);
        assertEquals("Recipe should have the new instructions", rep1.getInstructions(), newInstructions);
        assertEquals("Recipe should have the new privacy", rep1.getIsPrivate(), true);
        assertEquals("Recipe should have the new difficulty", rep1.getDifficulty(), newDifficulty);

        //TODO create tests for the exception that's thrown

        System.out.println("Recipe modify tested Successfully");
    }

    @Test
    public void testGetNullRecipe() {
        System.out.println("\nStarting null recipe search Tests:");




        assertNull("Result of nonexistent recipe id should be NULL", manager.getRecipeByID(100));
        assertNull("Result of nonexistent recipe search should be NULL",manager.getRecipeFromPersistence(testRep));

        System.out.println("Null recipe search tested Successfully");
    }
}
