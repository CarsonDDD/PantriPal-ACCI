package comp3350.acci.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.persistence.IngredientPersistence;

public class IngredientPersistenceStub implements IngredientPersistence {

    private List<Ingredient> ingredients;

    public IngredientPersistenceStub() {
        this.ingredients = new ArrayList<>();

        Ingredient pb = new Ingredient("Peanut Butter");
        Ingredient jelly = new Ingredient("Strawberry Jelly");
        Ingredient bread = new Ingredient("Bread");
        Ingredient butter = new Ingredient("Butter");

        ingredients.add(pb);
        ingredients.add(jelly);
        ingredients.add(bread);
        ingredients.add(butter);
    }

    @Override
    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    @Override
    public Ingredient insertIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return ingredient;
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        int index;

        index = ingredients.indexOf(ingredient);
        if (index >= 0)
        {
            ingredients.set(index, ingredient);
        }
        return ingredient;
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        int index;

        index = ingredients.indexOf(ingredient);
        if (index >= 0)
        {
            ingredients.remove(index);
        }
    }
}
