package comp3350.acci.objects.persistence;

import java.util.List;

import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;

public interface PantryPersistence {
    List<Pantry> getPantrys();

    Pantry insertPantry(Pantry pantry);

    Pantry updatePantry(Pantry pantry);

    List<Pantry> getPantrysByUser(User user);

    void deletePantry(Pantry pantry);
}
