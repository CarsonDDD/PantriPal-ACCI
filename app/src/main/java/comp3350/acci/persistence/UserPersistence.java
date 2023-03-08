package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.User;

public interface UserPersistence {
    List<User> getUsers();

    User getUser(int userID);

    User insertUser(User user);

    User updateUser(User user);

    void deleteUser(User user);
}
