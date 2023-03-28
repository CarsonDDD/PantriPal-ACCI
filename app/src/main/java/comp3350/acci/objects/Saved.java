package comp3350.acci.objects;

import java.util.Objects;

public class Saved {

    private User user;

    private Recipe recipe;

    public Saved(User user, Recipe recipe){
        this.user = user;
        this.recipe = recipe;
    }

    public User getUser(){
        return this.user;
    }

    public Recipe getRecipe(){
        return this.recipe;
    }

    public boolean equals(Object other) {
        boolean equals = false;

        if (other instanceof Saved)
        {
            final Saved otherSaved = (Saved) other;
            equals = Objects.equals(this.user, otherSaved.getUser()) && Objects.equals(this.recipe, otherSaved.getRecipe());
        }

        return equals;
    }

    public String toString(){
        return String.format("Saved: {%s, %s}", user.toString(), recipe.toString());
    }
}
