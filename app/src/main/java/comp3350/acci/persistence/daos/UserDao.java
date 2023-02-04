package comp3350.acci.persistence.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import comp3350.acci.objects.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Delete
    void delete(User user);
}
