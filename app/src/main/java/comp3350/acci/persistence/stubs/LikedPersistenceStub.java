package comp3350.acci.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.Liked;
import comp3350.acci.objects.Recipe;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.LikedPersistence;

public class LikedPersistenceStub implements LikedPersistence {

    private List<Liked> likedList;

    public LikedPersistenceStub() {
        this.likedList = new ArrayList<>();

        User user1 = new User(0, "600cows", "hi im ayden");
        User user2 = new User(1, "testuser", "bio");

        Recipe pbj = new Recipe(user1, 0, "PB&J", "Make PB&J", false, "easy");
        Recipe toast = new Recipe(user2, 1, "toast", "toast bread, spread butter", false, "easy");

        Liked liked1 = new Liked(user1, pbj);
        Liked liked2 = new Liked(user1, toast);
        Liked liked3 = new Liked(user2, pbj);

        likedList.add(liked1);
        likedList.add(liked2);
        likedList.add(liked3);

    }

    @Override
    public List<Liked> getLikeds() {
        return Collections.unmodifiableList(likedList);
    }

    @Override
    public Liked insertLiked(Liked liked) {
        likedList.add(liked);
        return liked;
    }

    @Override
    public List<Recipe> getLikedRecipesByUser(User user) {
        List<Recipe> result = new ArrayList<>();

        for (int i=0; i<likedList.size(); i++){
            if (likedList.get(i).getUser().equals(user)) {
                result.add(likedList.get(i).getRecipe());
            }
        }
        return result;
    }

    @Override
    public List<User> getUserLikesByRecipe(Recipe recipe) {
        return null;
    }

    @Override
    public void deleteLiked(Liked liked) {
        int index;

        index = likedList.indexOf(liked);
        if (index >= 0)
        {
            likedList.remove(index);
        }
    }
}
