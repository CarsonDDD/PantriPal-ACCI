package comp3350.acci.presentation;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class CommandLineInterface  // command-line interface
{
	public static BufferedReader console;
	public static String inputLine;
	public static String[] inputTokens;


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

	}

	public static void processGetCourse()
	{

	}

	public static void processGetSC()
	{

	}

	public static void processGetCS()
	{

	}
}