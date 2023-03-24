package comp3350.acci.business.interfaces;

import java.util.List;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public interface UserManager {
    public User createUser(String username, String userBio);
    public List<User> getUsers();


    public User getCurrUser();
    public void setCurrUser(User currUser);
    public List<Recipe> getUsersSavedRecipes(User user);
    public boolean toggleSaved(User user, Recipe recipe);
    public boolean isSaved(User user, Recipe recipe);
    public User getUser(int userID);

    public String getUsername(int userID);
    public void setUsername(int userID, String newUsername);
    public String getBio(int userID);
    void setBio(int userID, String newBio);
}
