package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.Saved;
import comp3350.acci.objects.User;

public interface SavedPersistence {
    List<Saved> getSaveds();

    Saved insertSaved(Saved saved);


    List<Recipe> getSavedRecipesByUser(User user);

    List<User> getUserSavesByRecipe(Recipe recipe);

    void deleteSaved(Saved saved);
}
