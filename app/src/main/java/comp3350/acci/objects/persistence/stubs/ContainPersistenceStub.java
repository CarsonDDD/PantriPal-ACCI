package comp3350.acci.objects.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.Contain;
import comp3350.acci.objects.Ingredient;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.objects.persistence.ContainPersistence;

public class ContainPersistenceStub implements ContainPersistence {

    private List<Contain> containList;

    public ContainPersistenceStub() {
        this.containList = new ArrayList<>();

        User user1 = new User(0, "600cows", "hi im ayden");
        User user2 = new User(1, "testuser", "bio");

        Ingredient pb = new Ingredient("Peanut Butter");
        Ingredient jelly = new Ingredient("Strawberry Jelly");
        Ingredient bread = new Ingredient("Bread");
        Ingredient butter = new Ingredient("Butter");

        Recipe pbj = new Recipe(user1,0, "PB&J", "Make PB&J", false, "easy");
        Recipe toast = new Recipe(user2,1, "toast", "toast bread, spread butter", false, "easy");

        Contain contain1 = new Contain(pbj, pb, 1);
        Contain contain2 = new Contain(pbj, jelly, 1);
        Contain contain3 = new Contain(pbj, bread, 2);
        Contain contain4 = new Contain(toast, bread, 1);
        Contain contain5 = new Contain(toast, butter, 0.25);

        containList.add(contain1);
        containList.add(contain2);
        containList.add(contain3);
        containList.add(contain4);
        containList.add(contain5);

    }

    @Override
    public List<Contain> getContains() {
        return Collections.unmodifiableList(containList);
    }

    @Override
    public Contain insertContain(Contain contain) {
        containList.add(contain);
        return contain;
    }

    @Override
    public Contain updateContain(Contain contain) {
        int index;

        index = containList.indexOf(contain);
        if (index >= 0)
        {
            containList.set(index, contain);
        }
        return contain;
    }

    @Override
    public List<Contain> getContainsByRecipe(Recipe recipe) {
        List<Contain> result = new ArrayList<>();

        for (int i=0; i<containList.size(); i++){
            if (containList.get(i).getRecipe().equals(recipe)) {
                result.add(containList.get(i));
            }
        }
        return result;
    }

    @Override
    public void deleteContain(Contain contain) {
        int index;

        index = containList.indexOf(contain);
        if (index >= 0)
        {
            containList.remove(index);
        }
    }
}
