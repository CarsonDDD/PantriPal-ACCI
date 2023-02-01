package comp3350.acci.presentation;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

import comp3350.acci.business.AccessStudents;
import comp3350.acci.business.AccessCourses;
import comp3350.acci.business.AccessSC;
import comp3350.acci.objects.Student;
import comp3350.acci.objects.Course;
import comp3350.acci.objects.SC;

public class CLI  // command-line interface
{
	public static BufferedReader console;
	public static String inputLine;
	public static String[] inputTokens;
	
	public static Student currentStudent;
	public static Course currentCourse;
	public static SC currentSC;
	public static SC currentCS;

	public static String studentNumber;
	public static String courseNumber;
	
	public static String indent = "  ";
	
	public static void run()
	{
		try
		{
			console = new BufferedReader(new InputStreamReader(System.in));
			process();
			console.close();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void process()
	{
		readLine();
		while ((inputLine != null) && (!inputLine.equalsIgnoreCase("exit"))
				&& (!inputLine.equalsIgnoreCase("quit"))
				&& (!inputLine.equalsIgnoreCase("q"))
				&& (!inputLine.equalsIgnoreCase("bye")))
		{	// use cntl-c or exit to exit
			inputTokens = inputLine.split("\\s+");
			parse();
			readLine();
		}
	}

	public static void readLine()
	{
		try
		{
			System.out.print(">");
			inputLine = console.readLine();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void parse()
	{
		if (inputTokens[0].equalsIgnoreCase("get"))
		{
			processGet();
		}
		else
		{
			System.out.println("Invalid command.");
		}
	}

	public static void processGet()
	{
		if (inputTokens[1].equalsIgnoreCase("Student"))
		{
			processGetStudent();
		}
		else if (inputTokens[1].equalsIgnoreCase("Course"))
		{
			processGetCourse();
		}
		else if (inputTokens[1].equalsIgnoreCase("SC"))
		{
			processGetSC();
		}
		else if (inputTokens[1].equalsIgnoreCase("CS"))
		{
			processGetCS();
		}
		else
		{
			System.out.println("Invalid data type");
		}
	}

	public static void processGetStudent()
	{
		AccessStudents accessStudents;
		AccessSC accessSC;
		
		accessStudents = new AccessStudents();
		
		if (inputTokens.length > 2)
		{
			if (inputTokens[2].equalsIgnoreCase("orphan"))
			{
				accessSC = new AccessSC();
				currentStudent = accessStudents.getSequential();			
				while (currentStudent != null)
				{
					studentNumber = currentStudent.getStudentID();
					accessSC = new AccessSC();
					currentSC = accessSC.getSC(studentNumber);
					if (currentSC == null)
					{
						System.out.println(indent +currentStudent);
					}
					currentStudent = accessStudents.getSequential();
				}
			}
			else
			{
				studentNumber = inputTokens[2];
				currentStudent = accessStudents.getRandom(studentNumber);
				System.out.println(indent +currentStudent);
			}
		}
		else
		{
			currentStudent = accessStudents.getSequential();			
			while (currentStudent != null)
			{
				studentNumber = currentStudent.getStudentID();
				System.out.println(indent +currentStudent);
				currentStudent = accessStudents.getSequential();
			}
		}
	}

	public static void processGetCourse()
	{
		AccessCourses accessCourses;
		AccessSC accessSC;

		accessCourses = new AccessCourses();
		
		if (inputTokens.length > 2)
		{
			if (inputTokens[2].equalsIgnoreCase("orphan"))
			{
				accessSC = new AccessSC();
				currentCourse = accessCourses.getSequential();			
				while (currentCourse != null)
				{
					courseNumber = currentCourse.getCourseID();
					accessSC = new AccessSC();
					currentCS = accessSC.getCS(courseNumber);
					if (currentCS == null)
					{
						System.out.println(indent +currentCourse);
					}
					currentCourse = accessCourses.getSequential();
				}
			}
			else
			{
				courseNumber = (inputTokens[2]).toUpperCase(Locale.getDefault());
				currentCourse = accessCourses.getRandom(courseNumber);
				System.out.println(indent +currentCourse);
			}
		}
		else
		{
			currentCourse = accessCourses.getSequential();			
			while (currentCourse != null)
			{
				courseNumber = currentCourse.getCourseID();
				System.out.println(indent +currentCourse);
				currentCourse = accessCourses.getSequential();
			}
		}
	}

	public static void processGetSC()
	{
		AccessSC accessSC;
		
		accessSC = new AccessSC();
		currentSC = accessSC.getSC(studentNumber);			
		while (currentSC != null)
		{
			System.out.println(indent +currentSC);
			currentSC = accessSC.getSC(studentNumber);
		}
	}

	public static void processGetCS()
	{
		AccessSC accessSC;
		
		accessSC = new AccessSC();
		currentCS = accessSC.getCS(courseNumber);			
		while (currentCS != null)
		{
			System.out.println(indent +currentCS);
			currentCS = accessSC.getCS(courseNumber);
		}
	}
}