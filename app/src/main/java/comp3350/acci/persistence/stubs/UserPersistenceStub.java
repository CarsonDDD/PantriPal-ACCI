package comp3350.acci.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.User;
import comp3350.acci.persistence.UserPersistence;

public class UserPersistenceStub implements UserPersistence {
    private List<User> users;

    private User currentUser;

    public UserPersistenceStub() {
        this.users = new ArrayList<>();

        User user1 = new User(0, "600cows", "hi im ayden");
        User user2 = new User(1, "testuser", "bio");

        users.add(user1);
        users.add(user2);;

        this.currentUser = user2;
    }
    @Override
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public User getUser(int userID) {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public User setCurrentUser(User user) {
        currentUser = user;
        return user;
    }


    @Override
    public User insertUser(User user) {
        // don't bother checking for duplicates
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        int index;

        index = users.indexOf(user);
        if (index >= 0)
        {
            users.set(index, user);
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        int index;

        index = users.indexOf(user);
        if (index >= 0)
        {
            users.remove(index);
        }
    }
}
