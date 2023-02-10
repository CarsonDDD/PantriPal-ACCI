package comp3350.acci.objects.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.User;
import comp3350.acci.objects.persistence.UserPersistence;

public class UserPersistenceStub implements UserPersistence {
    private List<User> users;

    public UserPersistenceStub() {
        this.users = new ArrayList<>();

        users.add(new User(0, "600cows", "hi im ayden"));
        users.add(new User(1, "testuser", "bio"));;
    }
    @Override
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
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
