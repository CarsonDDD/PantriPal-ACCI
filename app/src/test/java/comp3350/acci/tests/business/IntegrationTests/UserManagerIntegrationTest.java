package comp3350.acci.tests.business.IntegrationTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import comp3350.acci.business.implementation.UserCreator;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.Saved;
import comp3350.acci.objects.User;

import comp3350.acci.persistence.SavedPersistence;
import comp3350.acci.persistence.UserPersistence;
import comp3350.acci.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.acci.persistence.hsqldb.SavedPersistenceHSQLDB;
import comp3350.acci.tests.utils.TestFilesUtil;


public class UserManagerIntegrationTest extends TestCase {
    public UserManagerIntegrationTest(String arg0 ) {
        super(arg0);
    }
    UserPersistence userPers;
    private File tempDB;
    SavedPersistence savedPers;

    UserManager userManager;
    User testUser;
    @Before
    public void setUp() throws IOException {
        tempDB = TestFilesUtil.copyDB();
        userPers = new UserPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        savedPers = new SavedPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        userManager = new UserCreator(userPers, savedPers);
        testUser = new User("JohnnyAppleseed", "Hey! I'm Johnny Appleseed. Nice to meet you");

    }
    @After
    public void tearDown() {
        userPers = null;
        savedPers = null;
        userManager = null;
        testUser = null;
        tempDB = null;
    }

    @Test
    public void testCreateUser() {

        String username = "JohnnyAppleseed";
        String bio = "Hey! I'm Johnny Appleseed. Nice to meet you";

        User createdUser = userManager.createUser(username, bio);
        User receivedUser = userManager.getUser(createdUser.getUserID());

        assertEquals("User should have been added to persistence", createdUser, receivedUser);

    }

    @Test
    public void testUserBio() {

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
    @Test
    public void testSaved() {
        String username = "JohnnyAppleseed";
        String bio = "Hey! I'm Johnny Appleseed. Nice to meet you";
        User createdUser = userManager.createUser(username, bio);
        Recipe testRecipe = new Recipe(testUser, "PB&J", "Put peanut butter and jam on toast.", false, "Hard");

        assertTrue("ToggleSaved should return true for unsaved recipes", userManager.toggleSaved(createdUser, testRecipe));
    }
}
