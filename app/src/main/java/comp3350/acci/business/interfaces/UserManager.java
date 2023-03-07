package comp3350.acci.business.interfaces;

import java.util.List;

import comp3350.acci.objects.User;

public interface UserManager {
    public User createUser(String username, String userBio);
    public List<User> getUsers();
    public User setBio(String username, String newBio);


}
