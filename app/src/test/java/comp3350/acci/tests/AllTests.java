package comp3350.acci.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.acci.tests.business.RecipeInsertionTest;
import comp3350.acci.tests.objects.ContainTest;
import comp3350.acci.tests.objects.IngredientTest;
import comp3350.acci.tests.objects.LikedTest;
import comp3350.acci.tests.objects.PantryTest;
import comp3350.acci.tests.objects.RecipeTest;
import comp3350.acci.tests.objects.SavedTest;
import comp3350.acci.tests.objects.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        RecipeTest.class,
        IngredientTest.class,
        LikedTest.class,
        SavedTest.class,
        PantryTest.class,
        ContainTest.class,
        RecipeInsertionTest.class
})
public class AllTests
{

}
