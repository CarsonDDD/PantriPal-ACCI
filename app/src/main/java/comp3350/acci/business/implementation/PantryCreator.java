package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.PantryPersistence;

import comp3350.acci.business.interfaces.PantryManager;

public class PantryCreator implements PantryManager {

    private PantryPersistence pantryPersistence;

    public PantryCreator(PantryPersistence pantryPersistence){
        this.pantryPersistence = pantryPersistence;
    }

    public Pantry insertPantry(User user, Ingredient ingredient, double amount, String unit){
        Pantry result = null;
        if(user != null && ingredient != null && amount > 0){
            result = pantryPersistence.insertPantry(new Pantry(user, ingredient, amount, unit));
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
}
