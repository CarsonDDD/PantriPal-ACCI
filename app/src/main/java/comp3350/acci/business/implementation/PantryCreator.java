package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.IngredientPersistence;
import comp3350.acci.persistence.PantryPersistence;

import comp3350.acci.business.interfaces.PantryManager;

public class PantryCreator implements PantryManager {

    private PantryPersistence pantryPersistence;
    private IngredientPersistence ingredientPersistence;

    public PantryCreator(PantryPersistence pantryPersistence, IngredientPersistence ingredientPersistence){
        this.pantryPersistence = pantryPersistence;
        this.ingredientPersistence = ingredientPersistence;
    }

    public Pantry insertPantry(User user, Ingredient ingredient, double amount, String unit){
        Pantry result = null;
        if(!ingredientPersistence.getIngredients().contains(ingredient)){
            ingredientPersistence.insertIngredient(ingredient);
        }
        if(user != null && ingredient != null && amount > 0){
            boolean contains = false;
            for(Pantry pantry : pantryPersistence.getPantrysByUser(user)) {
                if (pantry.getIngredient().getName().equals(ingredient.getName())) {
                    contains = true;
                    break;
                }
            }
            if(!contains){
                result = pantryPersistence.insertPantry(new Pantry(user, ingredient, amount, unit));
            }
        }
        return result;
    }

    public Pantry updatePantry(User user, Ingredient ingredient, double amount, String unit){
        Pantry result = null;
        if(user != null && ingredient != null && amount > 0){
            result = pantryPersistence.updatePantry(new Pantry(user, ingredient, amount, unit));
        }
        return result;
    }

    public void deletePantry(Pantry pantry){
        if(pantry != null){
            pantryPersistence.deletePantry(pantry);
        }

    }

    public List<Pantry> getPantrys(){
        return pantryPersistence.getPantrys();
    }

    public List<Pantry> getPantrysByUser(User user) {
        List<Pantry> result = null;
        if(user != null){
            result = pantryPersistence.getPantrysByUser(user);
        }
        return result;
    }

    public List<Ingredient> getIngredients(){
        return ingredientPersistence.getIngredients();
    }
}
