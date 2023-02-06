package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Recipe;

public interface ContainPersistence {
    List<Contain> getContains();

    Contain insertPantry(Contain contain);

    Contain updatePantry(Contain contain);

    List<Contain> getContainsByRecipe(Recipe recipe);

    void deletePantry(Contain contain);
}
