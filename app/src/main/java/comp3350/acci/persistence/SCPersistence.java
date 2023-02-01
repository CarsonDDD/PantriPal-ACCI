package comp3350.acci.persistence;

import java.util.List;

import comp3350.acci.objects.SC;

public interface SCPersistence {
    List<SC> getSC(final String studentID);

    List<SC> getCS(final String courseID);
}
