package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.RecipePersistence;
import comp3350.acci.persistence.SavedPersistence;
import comp3350.acci.persistence.UserPersistence;

public class UserCreator implements UserManager {
    private UserPersistence userPersistence;
    private SavedPersistence savedPersistence;
    public UserCreator() {
        userPersistence = Services.getUserPersistence();
        savedPersistence = Services.getSavedPersistence();
    }

    //creates a DSO: User with the given username and bio
    public User createUser(String username, String userBio) {
        User newUser = new User(username, userBio);
        userPersistence.insertUser(newUser);
        return newUser;
    }

    @Override
    public List<User> getUsers() {
        return  userPersistence.getUsers();
    }

    public User setBio(int userID, String newBio) {
        User user = userPersistence.getUser(userID);
        user.setBio(newBio);
        user = userPersistence.updateUser(user);
        return user;
    }
    public User getCurrUser()  {
        return userPersistence.getCurrentUser();
    }
    public void setCurrUser(User currUser) {
        userPersistence.setCurrentUser(currUser);
    }
    public List<Recipe> getUsersSavedRecipes(User user) {
        return savedPersistence.getSavedRecipesByUser(user);
    }
    public User getUser(int userID) {
        return userPersistence.getUser(userID);
    }

    @Override
    public String getUsername(int userID) {
        return userPersistence.getUser(userID).getUserName();
    }

    @Override
    public String getBio(int userID) {
        return userPersistence.getUser(userID).getBio();
    }
}
