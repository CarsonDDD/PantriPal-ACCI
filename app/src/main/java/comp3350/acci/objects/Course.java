package comp3350.acci.objects;

import java.util.Objects;

public class Course
{
	private final String courseID;
	private final String courseName;

	public Course(final String newID)
	{
		courseID = newID;
		courseName = null;
	}

	public Course(final String newID, final String newCourseName)
	{
		courseID = newID;
		courseName = newCourseName;
	}

	public String getCourseID()
	{
		return (courseID);
	}

	public String getCourseName()
	{
		return (courseName);
	}

	public String toString()
	{
		return String.format("Course: %s %s", courseID, courseName);
	}

	public boolean equals(Object other)
	{
		boolean equals = false;
		if (other instanceof Course) {

			final Course otherCourse = (Course) other;
			equals = Objects.equals(this.courseID, otherCourse.courseID);

		}

		return equals;
	}
}