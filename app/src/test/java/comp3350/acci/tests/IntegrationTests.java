package comp3350.acci.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.acci.tests.business.IntegrationTests.PantryManagerIntegrationTest;
import comp3350.acci.tests.business.IntegrationTests.RecipeManagerIntegrationTest;
import comp3350.acci.tests.business.IntegrationTests.UserManagerIntegrationTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserManagerIntegrationTest.class,
        RecipeManagerIntegrationTest.class,
        PantryManagerIntegrationTest.class

})
public class IntegrationTests {
}
