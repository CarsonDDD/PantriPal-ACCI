package comp3350.acci.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import comp3350.acci.objects.User;
import comp3350.acci.persistence.daos.UserDao;
import comp3350.acci.presentation.CoursesActivity;

@Database(entities={User.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase setDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getDbInstance() {
        return INSTANCE;
    }

}
