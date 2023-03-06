package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.Liked;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.LikedPersistence;

public class AccessLiked {

    private LikedPersistence likedPersistence;

    public AccessLiked(){
        this.likedPersistence = Services.getLikedPersistence();
    }

    public List<Liked> getLikeds(){
        return likedPersistence.getLikeds();
    }

    public List<Recipe> getLikedRecipesByUser(User user){
        return likedPersistence.getLikedRecipesByUser(user);
    }

    public void insertLiked(User user, Recipe recipe){
        likedPersistence.insertLiked(new Liked(user, recipe));
    }

    public void deleteLiked(Liked liked){
        likedPersistence.deleteLiked(liked);
    }
}
