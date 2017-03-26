package bg.softuni.repository;

import bg.softuni.dataStructures.SimpleSortedList;
import bg.softuni.models.Course;
import bg.softuni.models.Student;

import java.util.Comparator;

public interface Requester {
    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);

    SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> cmp);

    SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> cmp);

}
