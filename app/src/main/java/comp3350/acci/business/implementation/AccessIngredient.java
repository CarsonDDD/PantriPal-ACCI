package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.persistence.IngredientPersistence;

public class AccessIngredient {

    private IngredientPersistence ingredientPersistence;

    public AccessIngredient(){
        this.ingredientPersistence = Services.getIngredientPersistence();
    }

    public List<Ingredient> getIngredients(){
        return ingredientPersistence.getIngredients();
    }

    public void insertIngredient(String name){
        ingredientPersistence.insertIngredient(new Ingredient(name));
    }

    public void updateIngredient(String name){
        ingredientPersistence.updateIngredient(new Ingredient(name));
    }

    public void deleteIngredient(Ingredient ingredient){
        ingredientPersistence.deleteIngredient(ingredient);
    }
}
