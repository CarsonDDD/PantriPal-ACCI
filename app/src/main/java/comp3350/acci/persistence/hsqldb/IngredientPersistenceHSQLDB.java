package comp3350.acci.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.IngredientPersistence;

public class IngredientPersistenceHSQLDB implements IngredientPersistence {

    private final String dbPath;

    public IngredientPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Ingredient fromResultSet(final ResultSet rs) throws SQLException {
        final String name = rs.getString("name");
        return new Ingredient(name);
    }

    @Override
    public List<Ingredient> getIngredients() {
        final List<Ingredient> ingredients = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM ingredient");
            while (rs.next())
            {
                final Ingredient ingredient = fromResultSet(rs);
                ingredients.add(ingredient);
            }
            rs.close();
            st.close();

            return ingredients;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public Ingredient getIngredient(String currentIngredient) {
        Ingredient ingredient = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM ingredient WHERE name=?");
            st.setString(1, currentIngredient);
            final ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                ingredient = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return ingredient;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Ingredient insertIngredient(Ingredient ingredient) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO ingredient (name) VALUES(?)");
            st.setString(1, ingredient.getName());
            st.executeUpdate();

            st.close();
            return ingredient;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM ingredient WHERE name = ?");
            st.setString(1, ingredient.getName());
            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
