package comp3350.acci.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.acci.objects.Liked;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public class LikedTest
{
	@Test
	public void testLiked()
	{
		Recipe recipe;

		User user;

		Liked liked;

		System.out.println("\nStarting testLiked");

		user = new User("600cows", "Hello, my name is Ayden");
		recipe = new Recipe(user, "PB&J", "Make PB&J", false, "easy");

		liked = new Liked(user, recipe);

		assertNotNull(liked);
		assertEquals(user, liked.getUser());
		assertEquals(recipe,liked.getRecipe());

		System.out.println(liked);

		System.out.println("Finished testLiked");
	}
}