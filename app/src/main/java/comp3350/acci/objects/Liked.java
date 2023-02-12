package comp3350.acci.objects;

public class Liked {

    private User user;

    private Recipe recipe;

    public Liked(User user, Recipe recipe){
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
        return String.format("liked: {%s, %s}", user.toString(), recipe.toString());
    }

}
