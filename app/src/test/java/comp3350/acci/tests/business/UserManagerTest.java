package comp3350.acci.tests.business;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import comp3350.acci.business.implementation.RecipeCreator;
import comp3350.acci.business.implementation.UserCreator;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.Saved;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.RecipePersistence;
import comp3350.acci.persistence.SavedPersistence;
import comp3350.acci.persistence.UserPersistence;
import comp3350.acci.persistence.stubs.SavedPersistenceStub;
import comp3350.acci.persistence.stubs.UserPersistenceStub;

public class UserManagerTest extends TestCase {
    public UserManagerTest(String arg0 ) {
        super(arg0);
    }
    @Mock
    UserPersistence userMock;
    @Mock
    SavedPersistence savedMock;

    UserManager userManager;
    User testUser;
    @Before
    public void setUp() {
        userMock = Mockito.mock(UserPersistence.class);
        savedMock = Mockito.mock(SavedPersistence.class);
        userManager = new UserCreator(userMock, savedMock);
        testUser = new User("JohnnyAppleseed", "Hey! I'm Johnny Appleseed. Nice to meet you");

        //define common mock behaviour
        when(userMock.insertUser(any())).thenReturn(testUser);
        when(userMock.getUser(anyInt())).thenReturn(testUser);
        when(userMock.updateUser(any())).thenReturn(testUser);


    }
    @After
    public void tearDown() {
        userMock = null;
        savedMock = null;
        userManager = null;
        testUser = null;
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

        List<Recipe> userSavedRecipes = new ArrayList<Recipe>();
        List<Saved> allSaveds = new ArrayList<Saved>();

        when(savedMock.getSavedRecipesByUser(any())).thenReturn(userSavedRecipes);

        when(savedMock.getSaveds()).thenReturn(allSaveds);


        assertTrue("ToggleSaved should return true for unsaved recipes", userManager.toggleSaved(createdUser, testRecipe));

        userSavedRecipes.add(testRecipe);
        allSaveds.add(new Saved(createdUser, testRecipe));

        assertFalse("ToggleSaved should have unsaved the recipe and returned false", userManager.toggleSaved(createdUser, testRecipe));

    }
}
