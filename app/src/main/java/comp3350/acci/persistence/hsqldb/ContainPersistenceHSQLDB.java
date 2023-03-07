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
import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Recipe;
import comp3350.acci.persistence.ContainPersistence;
import comp3350.acci.persistence.IngredientPersistence;
import comp3350.acci.persistence.RecipePersistence;

public class ContainPersistenceHSQLDB implements ContainPersistence {

    private final String dbPath;

    public ContainPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Contain fromResultSet(final ResultSet rs) throws SQLException {
        final String name = rs.getString("name");
        final int recipeID = rs.getInt("recipeID");
        final float quantity = rs.getFloat("quantity");
        final String unit = rs.getString("unit");
        RecipePersistence recipePersistence = Services.getRecipePersistence();
        IngredientPersistence ingredientPersistence = Services.getIngredientPersistence();
        Ingredient ing = ingredientPersistence.getIngredient(name);
        Recipe recipe = recipePersistence.getRecipeByID(recipeID);
        return new Contain(recipe, ing, quantity, unit);
    }

    @Override
    public List<Contain> getContains() {
        final List<Contain> contains = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM contain");
            while (rs.next())
            {
                final Contain contain = fromResultSet(rs);
                contains.add(contain);
            }
            rs.close();
            st.close();

            return contains;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Contain insertContain(Contain contain) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO contain (recipeID, name, quantity, unit) VALUES(?, ?, ?, ?)");
            st.setInt(1, contain.getRecipe().getRecipeID());
            st.setString(2, contain.getIngredient().getName());
            st.setFloat(3, (float) contain.getQuantity());
            st.setString(4, contain.getUnit());
            st.executeUpdate();

            st.close();
            return contain;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Contain updateContain(Contain contain) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE contain SET quantity = ?, unit = ? WHERE recipeID = ? AND name = ?");
            st.setFloat(1, (float) contain.getQuantity());
            st.setString(2, contain.getUnit());
            st.setInt(3, contain.getRecipe().getRecipeID());
            st.setString(4, contain.getIngredient().getName());
            st.executeUpdate();
            st.close();
            return contain;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Contain> getContainsByRecipe(Recipe recipe) {
        final List<Contain> contains = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM contain where recipeID = ?");
            st.setInt(1, recipe.getRecipeID());
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final Contain contain = fromResultSet(rs);
                contains.add(contain);
            }
            rs.close();
            st.close();

            return contains;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteContain(Contain contain) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM contain WHERE recipeID = ? AND name = ?");
            st.setInt(1, contain.getRecipe().getRecipeID());
            st.setString(2, contain.getIngredient().getName());
            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
