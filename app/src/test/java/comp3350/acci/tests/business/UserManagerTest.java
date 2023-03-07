package comp3350.acci.tests.business;

import junit.framework.TestCase;

import java.util.List;

import comp3350.acci.business.implementation.UserCreator;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.User;

public class UserManagerTest extends TestCase {

    public UserManagerTest(String arg0 ) {
        super(arg0);
    }

    public void testCreateUser() {
        UserManager userManager = new UserCreator();
        String username = "JohnnyAppleseed";
        String bio = "Hey! I'm Johnny Appleseed. Nice to meet you";
        User createdUser = userManager.createUser(username, bio);
        List<User> userList = userManager.getUsers();

        assertTrue("User have been added to persistence",userList.contains(createdUser));

    }

}
