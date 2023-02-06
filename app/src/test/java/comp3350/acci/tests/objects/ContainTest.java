package comp3350.acci.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public class ContainTest
{
	@Test
	public void testContain()
	{
		Ingredient pb;

		User user;

		Recipe recipe;

		Contain contain;

		System.out.println("\nStarting testContain");

		user = new User("600cows", "Hello, my name is Ayden");
		pb = new Ingredient("Peanut Butter");
		recipe = new Recipe(user, "PB&J", "Make PB&J", false, "easy");


		contain = new Contain(recipe, pb, 1);

		assertNotNull(contain);
		assertEquals(recipe, contain.getRecipe());
		assertEquals(pb, contain.getIngredient());
		assertEquals(1, contain.getQuantity(), 0.001);

		System.out.println(contain);

		System.out.println("Finished testContain");
	}
}