package comp3350.acci.objects;

import java.util.Objects;

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

    public boolean equals(Object other) {
        boolean equals = false;

        if (other instanceof Liked)
        {
            final Liked otherLiked = (Liked) other;
            equals = Objects.equals(this.user, otherLiked.getUser()) && Objects.equals(this.recipe, otherLiked.getRecipe());
        }

        return equals;
    }

    public String toString(){
        return String.format("liked: {%s, %s}", user.toString(), recipe.toString());
    }

}
