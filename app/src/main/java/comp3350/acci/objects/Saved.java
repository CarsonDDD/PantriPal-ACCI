package comp3350.acci.objects;

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

    public String toString(){
        return String.format("Saved: {%s, %s}", user.toString(), recipe.toString());
    }
}
