package comp3350.acci.business.interfaces;

import java.util.List;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.persistence.PantryPersistence;
import comp3350.acci.objects.User;

public interface PantryManager {

    Pantry insertPantry(User user, Ingredient ingredient, double amount, String unit);

    Pantry updatePantry(User user, Ingredient ingredient, double amount, String unit);

    void deletePantry(Pantry pantry);

    List<Pantry> getPantrys();

    List<Pantry> getPantrysByUser(User user);
}
