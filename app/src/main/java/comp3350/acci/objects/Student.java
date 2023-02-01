package comp3350.acci.objects;

import java.util.Objects;

public class Student
{
	private final String studentID;
	private final String studentName;
	private final String studentAddress;

	public Student(final String newID)
	{
		this.studentID = newID;
		this.studentName = null;
		this.studentAddress = null;
	}

	public Student(final String newID, final String newStudentName, final String newStudentAddress)
	{
		this.studentID = newID;
		this.studentName = newStudentName;
		this.studentAddress = newStudentAddress;
	}

	public String getStudentID()
	{
		return (studentID);
	}

	public String getStudentName()
	{
		return (studentName);
	}

	public String getStudentAddress()
	{
		return (studentAddress);
	}

	public String toString()
	{
		return String.format("Student: %s %s %s", studentID, studentName, studentAddress);
	}

	public boolean equals(Object other)
	{
		boolean equals = false;

		if (other instanceof Student)
		{
			final Student otherStudent = (Student) other;
			equals = Objects.equals(this.studentID, otherStudent.studentID);
		}

		return equals;
	}
}
