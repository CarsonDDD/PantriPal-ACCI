
package comp3350.acci.tests.business;

//java imports
import junit.framework.TestCase;
import org.junit.Test;

//project imports
import comp3350.acci.business.RecipeManager;
import comp3350.acci.objects.Recipe;

public class RecipeInsertionTest extends TestCase {

    private RecipeManager manager;

    public RecipeInsertionTest(String arg0 ) {
        super(arg0);
        manager = new RecipeManager();
    }

    @Test
    public void testCreateRecipe() {
        System.out.println("\nStarting recipe creation test");
        Recipe recipe1 = manager.createRecipe("Caelan", "PB&J", "Put peanute butter and jam on toast", false, "Hard");
        assertNotNull(recipe1);
        //Test that the recipe has the expected fields

    }
    @Test
    public void testRecipePrivacy() {

    }
}
