package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.Liked;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;

public interface LikedPersistence {
    List<Liked> getLikeds();

    Liked insertLiked(Liked liked);


    List<Recipe> getLikedRecipesByUser(User user);

    List<User> getUserLikesByRecipe(Recipe recipe);

    void deleteLiked(Liked liked);
}
