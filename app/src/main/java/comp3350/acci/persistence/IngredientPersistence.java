package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.Ingredient;

public interface IngredientPersistence {
    List<Ingredient> getIngredients();

    Ingredient insertIngredient(Ingredient ingredient);

    Ingredient getIngredient(String ingredient);

    void deleteIngredient(Ingredient ingredient);
}
