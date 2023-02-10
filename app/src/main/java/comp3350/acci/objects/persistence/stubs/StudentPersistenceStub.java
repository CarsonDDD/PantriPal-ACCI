package comp3350.acci.objects.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.acci.objects.Student;
import comp3350.acci.objects.persistence.StudentPersistence;

public class StudentPersistenceStub implements StudentPersistence {
    private List<Student> students;

    public StudentPersistenceStub() {
        this.students = new ArrayList<>();

        students.add(new Student("100", "Gary Chalmers", "Management"));
        students.add(new Student("200", "Selma Bouvier", "University Centre"));
        students.add(new Student("300", "Arnie Pye", "Frank Kennedy"));
        students.add(new Student("400", "Mary Bailey", "Off Campus"));
    }

    @Override
    public List<Student> getStudentSequential() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public List<Student> getStudentRandom(Student currentStudent) {
        List<Student> newStudents;
        int index;

        newStudents = new ArrayList<>();
        index = students.indexOf(currentStudent);
        if (index >= 0)
        {
            newStudents.add(students.get(index));
        }
        return newStudents;
    }

    @Override
    public Student insertStudent(Student currentStudent) {
        // don't bother checking for duplicates
        students.add(currentStudent);
        return currentStudent;
    }

    @Override
    public Student updateStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0)
        {
            students.set(index, currentStudent);
        }
        return currentStudent;
    }

    @Override
    public void deleteStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0)
        {
            students.remove(index);
        }
    }
}
