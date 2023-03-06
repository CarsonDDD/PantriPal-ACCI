package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.Saved;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.SavedPersistence;

public class AccessSaved {

    private SavedPersistence savedPersistence;

    public AccessSaved(){
        this.savedPersistence = Services.getSavedPersistence();
    }

    public List<Saved> getSaveds(){
        return savedPersistence.getSaveds();
    }

    public List<Recipe> getSavedRecipesByUser(User user){
        return savedPersistence.getSavedRecipesByUser(user);
    }

    public void insertSaved(User user, Recipe recipe){
        savedPersistence.insertSaved(new Saved(user, recipe));
    }

    public void deleteSaved(Saved saved){
        savedPersistence.deleteSaved(saved);
    }
}
