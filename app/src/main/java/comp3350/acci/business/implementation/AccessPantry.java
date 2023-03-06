package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.PantryPersistence;

public class AccessPantry {

    private PantryPersistence pantryPersistence;

    public AccessPantry(){
        this.pantryPersistence = Services.getPantryPersistence();
    }

    public List<Pantry> getPantrys(){
        return pantryPersistence.getPantrys();
    }

    public List<Pantry> getPantrysByUser(User user){
        return pantryPersistence.getPantrysByUser(user);
    }

    public void insertPantry(User user, Ingredient ingredient, double amount){
        pantryPersistence.insertPantry(new Pantry(user, ingredient, amount));
    }

    public void updatePantry(Pantry pantry){
        pantryPersistence.updatePantry(pantry);
    }

    public void deletePantry(Pantry pantry){
        pantryPersistence.deletePantry(pantry);
    }
}
