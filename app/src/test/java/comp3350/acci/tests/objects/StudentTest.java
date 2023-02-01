package comp3350.acci.tests.objects;

import org.junit.Test;

import comp3350.acci.objects.Student;
import static org.junit.Assert.*;

public class StudentTest
{
	@Test
	public void testStudent1()
	{
		Student student;
		
		System.out.println("\nStarting testStudent");
		
		student = new Student("123", "Joe", "12 Street");
		assertNotNull(student);
		assertTrue("123".equals(student.getStudentID()));
		assertTrue("Joe".equals(student.getStudentName()));
		assertTrue("12 Street".equals(student.getStudentAddress()));
		
		System.out.println("Finished testStudent");
	}
}