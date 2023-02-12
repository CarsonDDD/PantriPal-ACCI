package comp3350.acci.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public class RecipeTest
{
	@Test
	public void testRecipe1()
	{
		User user;
		Recipe recipe;
		
		System.out.println("\nStarting testRecipe");

		user = new User("600cows", "Hello, my name is Ayden");

		recipe = new Recipe(user, "PB&J", "Make PB&J", false, "easy");

		assertNotNull("Recipe should be non-null",recipe);
		assertEquals("Recipe should have correct name","PB&J", recipe.getName());
		assertEquals("Recipe should have the correct author",user, recipe.getAuthor());
		assertEquals("Recipe should have correct instructions","Make PB&J", recipe.getInstructions());
		assertFalse("recipe should have expected privacy (false)",recipe.getIsPrivate());
		assertEquals("Recipe difficulty should be correct","easy", recipe.getDifficulty());

		System.out.println(recipe);

		System.out.println("Finished testRecipe");
	}
}