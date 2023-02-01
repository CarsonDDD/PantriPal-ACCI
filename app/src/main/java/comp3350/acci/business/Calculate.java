package comp3350.acci.business;

import java.util.List;

import comp3350.acci.objects.SC;

public class Calculate
{
	public static String gpa(List<SC> elements)
	{
		final String[] grades = {"A+","A","B+","B","C+","C","D","F"};
		final double[] values = {4.5,4.0,3.5,3.0,2.5,2.0,1.0,0.0};
		SC element;
		String gpa;
		String grade;
		double gradeTotal;
		int elementCount;
		int validGrades;
		int missingGrades;
		int count;
		boolean found;

		validGrades = 0;
		missingGrades = 0;
		gradeTotal = 0;
		gpa = " ";
		if ((elements!=null) && (elements.size()>0))
		{
			for (elementCount=0; elementCount<elements.size(); elementCount++)
			{
				grade = "";
				if (!(elements.get(elementCount) instanceof SC))
				{	// Invalid or null element
					missingGrades = 0;
					validGrades = 0;
					elementCount = elements.size()+1;
					gpa = "?";
				}
				else
				{
					element = (SC) elements.get(elementCount);
					grade = ((SC) element).getGrade();
					found = false;
					if (grade.trim().equals(""))
					{	// found a course in progress, no grade yet
						missingGrades++;
					}
					else
					{
						for (count=0; count<grades.length&&!found; count++)
						{
							if (grades[count].equals(grade))
							{
								found = true;
								gradeTotal += values[count];
								validGrades++;
							}
						}
					}
				}
			}
			if (((validGrades+missingGrades)==elements.size()) && (validGrades>0))
			{
				gpa = "" +(gradeTotal/validGrades);
			}
			else if (missingGrades != elements.size())
			{	// Invalid grade
				gpa = "?";
			}
		}
		return gpa;
	}
}
