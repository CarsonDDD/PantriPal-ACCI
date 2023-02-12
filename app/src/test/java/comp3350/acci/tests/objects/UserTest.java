package comp3350.acci.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.acci.objects.User;

public class UserTest
{
	@Test
	public void testUser1()
	{
		User user;
		
		System.out.println("\nStarting testUser");

		user = new User("600cows", "Hello, my name is Ayden");
		assertNotNull("User should be non-null",user);
		assertEquals("user should have expected name","600cows", user.getUserName());
		assertEquals("user should have expected bio","Hello, my name is Ayden", user.getBio());

		System.out.println(user);

		user = new User();
		assertNotNull("empty constructor should be non-null",user);
		assertEquals("Empty constructor should produce null username",null, user.getUserName());
		assertEquals("empty constructor should produce null bio",null, user.getBio());


		System.out.println("Finished testUser");
	}
}