package main.com.KaPrim.models;

import java.util.Map;

public interface Course extends Comparable<Course> {
    String getName();

    Map<String, Student> getStudentsByName();

    void enrollStudent(Student studentImpl);
}