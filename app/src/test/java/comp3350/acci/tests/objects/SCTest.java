package comp3350.acci.tests.objects;

import org.junit.Test;

import comp3350.acci.objects.Course;
import comp3350.acci.objects.SC;
import comp3350.acci.objects.Student;
import static org.junit.Assert.*;

public class SCTest
{
	@Test
	public void testSC1()
	{
		SC sc;

		System.out.println("\nStarting testSC");

		final Student s = new Student("123", "Joe", "123 Fake Street");
		final Course c = new Course("12345", "Software Engineering I");
		sc = new SC(s, c, "A");
		assertNotNull(sc);
		assertTrue("123".equals(sc.getStudentID()));
		assertTrue("12345".equals(sc.getCourseID()));
		assertTrue("Joe".equals(sc.getStudentName()));
		assertTrue("Software Engineering I".equals(sc.getCourseName()));
		assertTrue("A".equals(sc.getGrade()));

		System.out.println("Finished testSC");
	}
}