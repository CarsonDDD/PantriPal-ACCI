package comp3350.acci.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;

public class PantryTest
{
	@Test
	public void testPantry()
	{
		Ingredient ingredient;

		User user;

		Pantry pantry;

		System.out.println("\nStarting testPantry");

		user = new User("600cows", "Hello, my name is Ayden");
		ingredient = new Ingredient("Peanut Butter");

		pantry = new Pantry(user, ingredient, 1);

		assertNotNull("Pantry should be non-null",pantry);
		assertEquals("Pantry should contain correct user",user, pantry.getUser());
		assertEquals("Pantry should contain the correct ingredient",ingredient,pantry.getIngredient());
		assertEquals("Pantry should have expected quantity",1, pantry.getQuantity(), 0.001);

		System.out.println(pantry);

		System.out.println("Finished testPantry");
	}
}