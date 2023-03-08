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
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.RecipePersistence;
import comp3350.acci.persistence.UserPersistence;

public class RecipePersistenceHSQLDB implements RecipePersistence {
    private final String dbPath;

    public RecipePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<Recipe> getRecipes() {
        final List<Recipe> recipes = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM RECIPE");
            while (rs.next())
            {
                final Recipe recipe = fromResultSet(rs);
                recipes.add(recipe);
            }
            rs.close();
            st.close();

            return recipes;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Recipe getRecipeByID(int id) {
        Recipe recipe = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM recipe WHERE recipeID=?");
            st.setInt(1, id);
            final ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                recipe = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return recipe;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Recipe getRecipe(Recipe recipe) {
        return getRecipeByID(recipe.getRecipeID());
    }

    @Override
    public List<Recipe> getUserRecipes(User user) {
        final List<Recipe> recipes = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM recipe WHERE authorID=?");
            st.setInt(1, user.getUserID());
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final Recipe recipe = fromResultSet(rs);
                recipes.add(recipe);
            }
            rs.close();
            st.close();

            return recipes;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Recipe insertRecipe(Recipe recipe) {
        try (final Connection c = connection()) {
            UserPersistence userPersistence = Services.getUserPersistence();
            User author = userPersistence.getUser(recipe.getAuthor().getUserID());
            if (author == null) {
                author = userPersistence.getUser(1); // use test user
            }
            recipe.setAuthor(author);
            final PreparedStatement st = c.prepareStatement("INSERT INTO recipe (authorID, instructions, name, isPrivate, difficulty) VALUES(?, ?, ?, ?, ?)");
            st.setInt(1, recipe.getAuthor().getUserID());
            st.setString(2, recipe.getInstructions());
            st.setString(3, recipe.getName());
            st.setBoolean(4, recipe.getIsPrivate());
            st.setString(5, recipe.getDifficulty());
            st.executeUpdate();
            return recipe;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE recipe SET authorID = ?, instructions = ?, name = ?, isPrivate = ?, difficulty = ? WHERE recipeID = ?");
            st.setInt(1, recipe.getAuthor().getUserID());
            st.setString(2, recipe.getInstructions());
            st.setString(3, recipe.getName());
            st.setBoolean(4, recipe.getIsPrivate());
            st.setString(5, recipe.getDifficulty());
            st.setInt(6, recipe.getRecipeID());
            st.executeUpdate();
            st.close();
            return recipe;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM recipe WHERE recipeID = ?");
            st.setInt(1, recipe.getRecipeID());
            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }

    private Recipe fromResultSet(final ResultSet rs) throws SQLException {
        final int recipeID = rs.getInt("recipeID");
        final String name = rs.getString("name");
        UserPersistence userPersistence = Services.getUserPersistence();
        final User author = userPersistence.getUser(rs.getInt("authorID"));
        final String instructions = rs.getString("instructions");
        final String difficulty = rs.getString("difficulty");
        final boolean isPrivate = rs.getBoolean("isPrivate");
        return new Recipe(author, recipeID, name, instructions, isPrivate, difficulty);
    }
}
