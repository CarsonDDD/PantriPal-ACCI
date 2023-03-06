package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Recipe;
import comp3350.acci.persistence.ContainPersistence;

public class AccessContain {

    private ContainPersistence containPersistence;

    public AccessContain(){
        this.containPersistence = Services.getContainPersistence();
    }

    public List<Contain> getContains(){
        return containPersistence.getContains();
    }

    public void insertContain(Recipe recipe, Ingredient ingredient, double amount){
        containPersistence.insertContain(new Contain(recipe, ingredient, amount));
    }

    public void updateContain(Recipe recipe, Ingredient ingredient, double amount){
        containPersistence.updateContain(new Contain(recipe, ingredient, amount));
    }

    public List<Contain> getContainsByRecipe(Recipe recipe){
        return getContainsByRecipe(recipe);
    }

    public void deleteContain(Contain contain){
        containPersistence.deleteContain(contain);
    }
}
