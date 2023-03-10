package comp3350.acci.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.acci.objects.User;
import comp3350.acci.persistence.UserPersistence;

public class UserPersistenceHSQLDB implements UserPersistence {
    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException {
        final int userID = rs.getInt("userID");
        final String userName = rs.getString("userName");
        final String bio = rs.getString("bio");
        return new User(userID, userName, bio);
    }


    @Override
    public List<User> getUsers() {
        final List<User> users = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM user");
            while (rs.next())
            {
                final User user = fromResultSet(rs);
                users.add(user);
            }
            rs.close();
            st.close();

            return users;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public User getUser(int id) {
        User user = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM user WHERE userID=?");
            st.setString(1, Integer.toString(id));
            final ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                user = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return user;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public User getCurrentUser() {
        User user = null;

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM keyvalues WHERE key='current_user'");
            if (rs.next())
            {
                int userID = rs.getInt("value");
                user = getUser(userID);
            }
            rs.close();
            st.close();

            return user;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public User setCurrentUser(User user) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE keyvalues SET value = ? where key = ?");
            st.setInt(1, user.getUserID());
            st.setString(2, "current_user");
            st.executeUpdate();
            st.close();
            return user;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public User insertUser(User user) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO user (userName, bio) VALUES(?, ?)");
            st.setString(1, user.getUserName());
            st.setString(2, user.getBio());
            st.executeUpdate();

            final PreparedStatement st2 = c.prepareStatement("SELECT * FROM user WHERE userName = ? AND bio = ?");
            st2.setString(1, user.getUserName());
            st2.setString(2, user.getBio());
            final ResultSet rs = st2.executeQuery();
            if (rs.next())
            {
                user = fromResultSet(rs);
            }

            rs.close();
            st.close();
            st2.close();

            return user;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public User updateUser(User user) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE user SET userName = ?, bio = ? where userID = ?");
            st.setString(1, user.getUserName());
            st.setString(2, user.getBio());
            st.setInt(3, user.getUserID());
            st.executeUpdate();
            st.close();
            return user;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteUser(User user) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM user WHERE userID = ?");
            st.setInt(1, user.getUserID());
            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
