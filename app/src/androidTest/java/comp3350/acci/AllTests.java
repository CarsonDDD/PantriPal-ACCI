package comp3350.acci;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
    public static Test suite() {
        TestSuite suite = new TestSuite("all tests");
        suite.addTest(new JUnit4TestAdapter(UserTest.class));
        return suite;
    }
}
