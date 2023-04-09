package comp3350.acci.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Pantry;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.PantryPersistence;

public class PantryPersistenceStub implements PantryPersistence {

    private List<Pantry> pantryList;

    public PantryPersistenceStub() {
        this.pantryList = new ArrayList<>();

        User user1 = new User(0, "600cows", "hi im ayden");
        User user2 = new User(1, "testuser", "bio");

        Ingredient pb = new Ingredient("Peanut Butter");
        Ingredient jelly = new Ingredient("Strawberry Jelly");
        Ingredient bread = new Ingredient("Bread");
        Ingredient butter = new Ingredient("Butter");

        Pantry pantry1 = new Pantry(user1, pb, 3);
        Pantry pantry2 = new Pantry(user1, jelly, 0.5);
        Pantry pantry3 = new Pantry(user1, bread, 6);
        Pantry pantry4 = new Pantry(user2, butter, 3);
        Pantry pantry5 = new Pantry(user2, bread, 3);

        pantryList.add(pantry1);
        pantryList.add(pantry2);
        pantryList.add(pantry3);
        pantryList.add(pantry4);
        pantryList.add(pantry5);
    }

    @Override
    public List<Pantry> getPantrys() {
        return Collections.unmodifiableList(pantryList);
    }

    @Override
    public Pantry insertPantry(Pantry pantry) {
        pantryList.add(pantry);
        return pantry;
    }

    @Override
    public Pantry updatePantry(Pantry pantry) {
        /*
        int index;

        index = pantryList.indexOf(pantry);
        if (index >= 0)
        {
            pantryList.set(index, pantry);
        }
        return pantry;
        */

        Pantry result = null;

        for(Pantry  p : pantryList){
            if(pantry.getUser().getUserName().equals(p.getUser().getUserName()) && pantry.getIngredient().getName().equals(p.getIngredient().getName())){
                p.setQuantity(pantry.getQuantity());
                p.setUnit(pantry.getUnit());
                result = p;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Pantry> getPantrysByUser(User user) {
        List<Pantry> result = new ArrayList<>();

        for (int i=0; i<pantryList.size(); i++){
            if (pantryList.get(i).getUser().equals(user)) {
                result.add(pantryList.get(i));
            }
        }
        return result;
    }

    @Override
    public void deletePantry(Pantry pantry) {
        int index;

        index = pantryList.indexOf(pantry);
        if (index >= 0)
        {
            pantryList.remove(index);
        }
    }
}
