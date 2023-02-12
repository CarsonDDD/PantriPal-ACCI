package comp3350.acci.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.acci.objects.Ingredient;

public class IngredientTest
{
	@Test
	public void testIngredient1()
	{
		Ingredient ingredient;
		
		System.out.println("\nStarting testIngredient");

		ingredient = new Ingredient("Peanut Butter");

		assertNotNull("ingredient should be non null",ingredient);
		assertEquals("ingredient name should be as expected","Peanut Butter", ingredient.getName());

		System.out.println(ingredient);

		System.out.println("Finished testIngredient");
	}
}