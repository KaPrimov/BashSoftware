package com.KaPrim.models;

import com.KaPrim.staticData.ExceptionMessages;

import java.util.LinkedHashMap;

public class Course {
    public static final int NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final int MAX_SCORE_ON_EXAM_TASK = 100;

    private String name;
    private LinkedHashMap<String, Student> studentsByName;

    public Course(String name) {
        setName(name);
        this.studentsByName = new LinkedHashMap<>();
    }

    public void setName(String name) {
        if(name == null || name.equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_OR_EMPY_VALUE);
        }
        this.name = name;
    }

    public void enrollStudent(Student student) {
        if(this.studentsByName.containsKey(student.getUserName())) {
            throw new IllegalArgumentException(String.format(
                    ExceptionMessages.STUDENT_ALREADY_ENROLLED_IN_GIVEN_COURSE,
                    student.getUserName(), this.name
            ));
        }
        this.studentsByName.put(student.getUserName(), student);
    }

    public LinkedHashMap<String, Student> getStudentsByName() {
        return new LinkedHashMap<>(this.studentsByName);
    }

    public String getName() {
        return name;
    }
}
