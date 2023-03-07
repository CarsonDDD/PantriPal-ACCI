package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.business.interfaces.UserManager;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.UserPersistence;

public class UserCreator implements UserManager {
    private UserPersistence userPersistence;

    public UserCreator() {
        userPersistence = Services.getUserPersistence();
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

    @Override
    public User setBio(String username, String newBio) {
        return null; //TODO getUser needs to be implemented in persistence
    }
}
