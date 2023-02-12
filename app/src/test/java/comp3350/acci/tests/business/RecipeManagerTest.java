
package comp3350.acci.tests.business;

//java imports
import junit.framework.TestCase;
import org.junit.Test;

//project imports
import comp3350.acci.business.RecipeManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public class RecipeManagerTest extends TestCase {

    public RecipeManagerTest(String arg0 ) {
        super(arg0);
    }

    @Test
    public void testCreateRecipe() {
        System.out.println("\nStarting recipe creation test");
        RecipeManager manager = new RecipeManager();
        User user = new User("Caelan", "I'm the coolest");
        Recipe rep1 = manager.createRecipe(user, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");

        Recipe manRep = manager.getRecipeFromPersistence(rep1);

        assertNotNull(rep1);
        assertNotNull(manRep);

        assertEquals(user, manRep.getAuthor());

        assertEquals("PB&J", rep1.getName());
        assertEquals("Put peanut butter and jam on toast.", rep1.getInstructions());
        assertFalse(rep1.getIsPrivate());
        System.out.println("Created Recipe had all expected fields");
    }
    @Test
    public void testUserlessCreateRecipe() {
        System.out.println("\nStarting userless recipe creation test");
        RecipeManager manager = new RecipeManager();
        String authorName = "Caelan";
        Recipe rep1 = manager.createRecipe(authorName, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");

        Recipe manRep = manager.getRecipeFromPersistence(rep1);

        assertNotNull("Recipe should not be NULL",rep1);
        assertNotNull("Recipe Manager should not be NULL", manRep);

        assertEquals("author name values should match", authorName, manRep.getAuthor().getUserName());

        assertEquals("Name should be the same", "PB&J", rep1.getName());
        assertEquals("Instructions should be the same","Put peanut butter and jam on toast.", rep1.getInstructions());
        assertFalse("Private bool should be false", rep1.getIsPrivate());
        System.out.println("Created Userless Recipe had all expected fields");
    }
    @Test
    public void testRecipePrivacy() {
        System.out.println("\nStarting Recipe privacy Test:");

        User user = new User("Caelan", "I'm the coolest");
        RecipeManager manager = new RecipeManager();

        Recipe rep1 = manager.createRecipe(user, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");

        assertFalse(rep1.getIsPrivate());

        manager.changePrivacy(rep1);

        assertTrue(rep1.getIsPrivate());

        System.out.println("changePrivacy tested successfully");
    }
    @Test
    public void testRecipeInstructions() {
        System.out.println("\nStarting recipe instruction Tests:");

        User user = new User("Caelan", "I'm the coolest");
        RecipeManager manager = new RecipeManager();

        Recipe rep1 = manager.createRecipe(user, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");

        assertNotNull(rep1.getInstructions());
        assertNotNull(manager.getInstructions(rep1));

        assertEquals(rep1.getInstructions(), manager.getInstructions(rep1));

        String newInstructions = "Put the jam and peanut butter on some bread";

        manager.setInstructions(rep1, newInstructions);

        assertEquals(newInstructions, manager.getInstructions(rep1));
        assertEquals(newInstructions, manager.getInstructionsByID(rep1.getRecipeID()));

        System.out.println("Recipe Instructions tested Successfully");
    }
    @Test
    public void testGetNullRecipe() {
        System.out.println("\nStarting null recipe search Tests:");

        RecipeManager manager = new RecipeManager();

        User user = new User("Caelan", "I'm the coolest");
        Recipe rep1 = new Recipe(user, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");


        assertNull("Result of nonexistent recipe id should be NULL", manager.getRecipeByID(100));
        assertNull("Result of nonexistent recipe search should be NULL",manager.getRecipeFromPersistence(rep1));

        System.out.println("Null recipe search tested Successfully");
    }
}
