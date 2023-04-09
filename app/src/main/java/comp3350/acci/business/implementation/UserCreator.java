package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.Saved;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.RecipePersistence;
import comp3350.acci.persistence.SavedPersistence;
import comp3350.acci.persistence.UserPersistence;

public class UserCreator implements UserManager {
    private UserPersistence userPersistence;
    private SavedPersistence savedPersistence;
    public UserCreator(UserPersistence up, SavedPersistence sp) {
        userPersistence = up;
        savedPersistence = sp;
    }

    //creates a DSO: User with the given username and bio
    public User createUser(String username, String userBio) {
        User newUser = new User(username, userBio);
        newUser = userPersistence.insertUser(newUser);
        return newUser;
    }

    @Override
    public List<User> getUsers() {
        return  userPersistence.getUsers();
    }

    public void setBio(int userID, String newBio) {
        User user = userPersistence.getUser(userID);
        user.setBio(newBio);
        user = userPersistence.updateUser(user);
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
    public boolean isSaved(User user, Recipe recipe) {
        List<Recipe> userSaved = savedPersistence.getSavedRecipesByUser(user);
        return userSaved.contains(recipe);
    }

    public boolean toggleSaved(User user, Recipe recipe) {
        boolean result = false;
        List<Recipe> userSaved =  savedPersistence.getSavedRecipesByUser(user);

        if(userSaved.contains(recipe)) {
            List<Saved> saves = savedPersistence.getSaveds();
            Saved toRemove = null;
            for (Saved item : saves) {
                if(item.getUser().equals(user) && item.getRecipe().equals(recipe)) {
                    toRemove = item;
                    break;
                }
            }
            if(toRemove != null) {
                savedPersistence.deleteSaved(toRemove);
                result = false;
            }

        }else {
            Saved newSaved = new Saved(user, recipe);
            savedPersistence.insertSaved(newSaved);
            result = true;
        }
        return result;
    }


    public User getUser(int userID) {
        return userPersistence.getUser(userID);
    }

    @Override
    public String getUsername(int userID) {
        return userPersistence.getUser(userID).getUserName();
    }

    public void setUsername(int userID, String newUsername) {
        User user = userPersistence.getUser(userID);
        user.setUserName(newUsername);
        userPersistence.updateUser(user);
    }
    @Override
    public String getBio(int userID) {
        return userPersistence.getUser(userID).getBio();
    }
}
