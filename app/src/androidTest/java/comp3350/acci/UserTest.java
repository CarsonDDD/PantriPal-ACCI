package comp3350.acci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import comp3350.acci.objects.User;
import comp3350.acci.persistence.AppDatabase;
import comp3350.acci.persistence.daos.UserDao;

@RunWith(AndroidJUnit4.class)
public class UserTest
{
	private UserDao userDao;
	private AppDatabase db;

	@Before
	public void createDb(){
		Context context = ApplicationProvider.getApplicationContext();
		db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
		userDao = db.userDao();
	}

	@After
	public void closeDb(){
		db.close();
	}

	@Test
	public void testUser()
	{
		System.out.println("\nStarting testUser");

		User user = new User("600cows", "Hello, my name is Ayden");
		assertNotNull(user);

		System.out.println(user.uid);

		userDao.insertUser(user);

		List<User> allUsers = db.userDao().getAllUsers();
		assertNotNull(allUsers);

		System.out.println(allUsers);

		assertEquals("600cows", allUsers.get(0).userName);
		assertEquals("Hello, my name is Ayden", allUsers.get(0).bio);

		System.out.println("Finished testUser");
	}
}