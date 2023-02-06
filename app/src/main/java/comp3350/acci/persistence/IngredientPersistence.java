package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.Ingredient;

public interface IngredientPersistence {
    List<Ingredient> getUsers();

    Ingredient insertUser(Ingredient ingredient);

    Ingredient updateUser(Ingredient ingredient);

    void deleteUser(Ingredient ingredient);
}
