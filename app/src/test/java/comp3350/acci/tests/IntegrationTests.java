package comp3350.acci.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.acci.tests.business.RecipeManagerIntegrationTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        RecipeManagerIntegrationTest.class
})
public class IntegrationTests {
}
