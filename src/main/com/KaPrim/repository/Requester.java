package main.com.KaPrim.repository;

import main.com.KaPrim.dataStructures.SimpleSortedList;
import main.com.KaPrim.models.Course;
import main.com.KaPrim.models.Student;

import java.util.Comparator;

public interface Requester {
    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);

    SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> cmp);

    SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> cmp);

}
