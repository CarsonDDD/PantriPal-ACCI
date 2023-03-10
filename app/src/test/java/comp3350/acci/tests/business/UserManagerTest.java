package comp3350.acci.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;

import comp3350.acci.business.implementation.UserCreator;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.stubs.SavedPersistenceStub;
import comp3350.acci.persistence.stubs.UserPersistenceStub;

public class UserManagerTest extends TestCase {
    public UserManagerTest(String arg0 ) {
        super(arg0);
    }
    @Test
    public void testCreateUser() {
        UserManager userManager = new UserCreator(new UserPersistenceStub(), new SavedPersistenceStub());
        String username = "JohnnyAppleseed";
        String bio = "Hey! I'm Johnny Appleseed. Nice to meet you";
        User createdUser = userManager.createUser(username, bio);
        List<User> userList = userManager.getUsers();

        assertTrue("User have been added to persistence",userList.contains(createdUser));

    }

    @Test
    public void testUserBio() {
        UserManager userManager = new UserCreator(new UserPersistenceStub(), new SavedPersistenceStub());
        String username = "JohnnyAppleseed";
        String bio = "Hey! I'm Johnny Appleseed. Nice to meet you";
        User createdUser = userManager.createUser(username, bio);
        String testBio = userManager.getBio(createdUser.getUserID());
        assertEquals("Bio should be the same as persistent bio", bio, testBio);

        String newBio = "This is a new bio written by me";
        userManager.setBio(createdUser.getUserID(), newBio);

        String changedBio = userManager.getBio(createdUser.getUserID());
        assertEquals("Bio should be changed after setter", newBio, changedBio);
    }

    @Test
    public void testUsername() {
        UserManager userManager = new UserCreator(new UserPersistenceStub(), new SavedPersistenceStub());
        String username = "JohnnyAppleseed";
        String bio = "Hey! I'm Johnny Appleseed. Nice to meet you";
        User createdUser = userManager.createUser(username, bio);
        String testUsername = userManager.getUsername(createdUser.getUserID());
        assertEquals("Username should be the same as persisten username",username, testUsername);

        String newName = "Mary Poppins";
        userManager.setUsername(createdUser.getUserID(), newName);

        String changeName = userManager.getUsername(createdUser.getUserID());
        assertEquals("Username should be changed after setter", newName, changeName);
    }

}
