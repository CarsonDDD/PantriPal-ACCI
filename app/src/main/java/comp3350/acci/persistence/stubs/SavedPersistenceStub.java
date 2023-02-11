package comp3350.acci.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.Saved;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.SavedPersistence;

public class SavedPersistenceStub implements SavedPersistence {

    private List<Saved> savedList;

    public SavedPersistenceStub() {
        this.savedList = new ArrayList<>();

        User user1 = new User(0, "600cows", "hi im ayden");
        User user2 = new User(1, "testuser", "bio");

        Recipe pbj = new Recipe(user1, 0, "PB&J", "Make PB&J", false, "easy");
        Recipe toast = new Recipe(user2, 1, "toast", "toast bread, spread butter", false, "easy");

        Saved saved1 = new Saved(user1, pbj);
        Saved saved2 = new Saved(user1, toast);
        Saved saved3 = new Saved(user2, toast);

        savedList.add(saved1);
        savedList.add(saved2);
        savedList.add(saved3);

    }

    @Override
    public List<Saved> getSaveds() {
        return Collections.unmodifiableList(savedList);
    }

    @Override
    public Saved insertSaved(Saved saved) {
        savedList.add(saved);
        return saved;
    }

    @Override
    public Saved updateSaved(Saved saved) {
        int index;

        index = savedList.indexOf(saved);
        if (index >= 0)
        {
            savedList.set(index, saved);
        }
        return saved;
    }

    @Override
    public List<Recipe> getSavedRecipesByUser(User user) {
        List<Recipe> result = new ArrayList<>();

        for (int i=0; i<savedList.size(); i++){
            if (savedList.get(i).getUser().equals(user)) {
                result.add(savedList.get(i).getRecipe());
            }
        }
        return result;
    }

    @Override
    public void deleteSaved(Saved saved) {

    }
}
