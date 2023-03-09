package comp3350.acci.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.IngredientPersistence;
import comp3350.acci.persistence.PantryPersistence;
import comp3350.acci.persistence.UserPersistence;

public class PantryPersistenceHSQLDB implements PantryPersistence {

    private final String dbPath;

    public PantryPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Pantry fromResultSet(final ResultSet rs) throws SQLException {
        final String name = rs.getString("name");
        final int userID = rs.getInt("userID");
        final float quantity = rs.getFloat("quantity");
        final String unit = rs.getString("unit");
        UserPersistence userPersistence = Services.getUserPersistence();
        IngredientPersistence ingredientPersistence = Services.getIngredientPersistence();
        Ingredient ing = ingredientPersistence.getIngredient(name);
        User user = userPersistence.getUser(userID);
        return new Pantry(user, ing, quantity, unit);
    }

    @Override
    public List<Pantry> getPantrys() {
        final List<Pantry> pantrys = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM pantry");
            while (rs.next())
            {
                final Pantry pantry = fromResultSet(rs);
                pantrys.add(pantry);
            }
            rs.close();
            st.close();

            return pantrys;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Pantry insertPantry(Pantry pantry) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO pantry (userID, name, quantity, unit) VALUES(?, ?, ?, ?)");
            st.setInt(1, pantry.getUser().getUserID());
            st.setString(2, pantry.getIngredient().getName());
            st.setFloat(3, (float) pantry.getQuantity());
            st.setString(4, pantry.getUnit());
            st.executeUpdate();

            st.close();
            return pantry;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Pantry updatePantry(Pantry pantry) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE pantry SET quantity = ?, unit = ? WHERE userID = ? AND name = ?");
            st.setFloat(1, (float) pantry.getQuantity());
            st.setString(2, pantry.getUnit());
            st.setInt(3, pantry.getUser().getUserID());
            st.setString(4, pantry.getIngredient().getName());
            st.executeUpdate();
            st.close();
            return pantry;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Pantry> getPantrysByUser(User user) {
        final List<Pantry> pantrys = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM pantry where userID = ?");
            st.setInt(1, user.getUserID());
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final Pantry pantry = fromResultSet(rs);
                pantrys.add(pantry);
            }
            rs.close();
            st.close();

            return pantrys;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deletePantry(Pantry pantry) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM pantry WHERE userID = ? AND name = ?");
            st.setInt(1, pantry.getUser().getUserID());
            st.setString(2, pantry.getIngredient().getName());
            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
