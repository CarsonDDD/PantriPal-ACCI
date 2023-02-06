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
		assertNotNull(user);
		assertEquals("600cows", user.getUserName());
		assertEquals("Hello, my name is Ayden", user.getBio());

		System.out.println(user);

		user = new User();
		assertNotNull(user);
		assertEquals(null, user.getUserName());
		assertEquals(null, user.getBio());


		System.out.println("Finished testUser");
	}
}