package comp3350.acci.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.Saved;
import comp3350.acci.objects.User;

public class SavedTest
{
	@Test
	public void testSaved()
	{
		Recipe recipe;

		User user;

		Saved saved;

		System.out.println("\nStarting testSaved");

		user = new User("600cows", "Hello, my name is Ayden");
		recipe = new Recipe(user, "PB&J", "Make PB&J", false, "easy");

		saved = new Saved(user, recipe);

		assertNotNull("Saved should be non-null",saved);
		assertEquals("Saved should have correct user",user, saved.getUser());
		assertEquals("Saved should have correct recipe",recipe,saved.getRecipe());

		System.out.println(saved);

		System.out.println("Finished testSaved");
	}
}