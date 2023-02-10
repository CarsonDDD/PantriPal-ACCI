package comp3350.acci.objects.persistence;

import java.util.List;

import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Recipe;

public interface ContainPersistence {
    List<Contain> getContains();

    Contain insertContain(Contain contain);

    Contain updateContain(Contain contain);

    List<Contain> getContainsByRecipe(Recipe recipe);

    void deleteContain(Contain contain);
}
