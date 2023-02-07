package comp3350.acci.application;

import comp3350.acci.persistence.CoursePersistence;
import comp3350.acci.persistence.SCPersistence;
import comp3350.acci.persistence.StudentPersistence;
import comp3350.acci.persistence.stubs.CoursePersistenceStub;
import comp3350.acci.persistence.stubs.SCPersistenceStub;
import comp3350.acci.persistence.stubs.StudentPersistenceStub;

public class Services
{
	private static StudentPersistence studentPersistence = null;
	private static CoursePersistence coursePersistence = null;
	private static SCPersistence scPersistence = null;

	public static synchronized StudentPersistence getStudentPersistence()
    {
		if (studentPersistence == null)
		{
		    studentPersistence = new StudentPersistenceStub();
        }

        return studentPersistence;
	}

    public static synchronized CoursePersistence getCoursePersistence()
    {
        if (coursePersistence == null)
        {
            coursePersistence = new CoursePersistenceStub();
        }

        return coursePersistence;
    }

	public static synchronized SCPersistence getScPersistence() {
        if (scPersistence == null) {
            scPersistence = new SCPersistenceStub();
        }

        return scPersistence;
    }
}
