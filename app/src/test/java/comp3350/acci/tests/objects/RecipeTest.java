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

		assertNotNull(recipe);
		assertEquals("PB&J", recipe.getName());
		assertEquals(user, recipe.getAuthor());
		assertEquals("Make PB&J", recipe.getInstructions());
		assertFalse(recipe.getIsPrivate());
		assertEquals("easy", recipe.getDifficulty());

		System.out.println(recipe);

		System.out.println("Finished testRecipe");
	}
}