package com.KaPrim.repository;

import com.KaPrim.dataStructures.SimpleSortedList;
import com.KaPrim.models.Course;
import com.KaPrim.models.Student;

import java.util.Comparator;

public interface Requester {
    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);

    SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> cmp);

    SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> cmp);

}
