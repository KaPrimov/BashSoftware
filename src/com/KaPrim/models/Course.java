package com.KaPrim.models;

import com.KaPrim.exceptions.DuplicateEntryInStructureException;
import com.KaPrim.exceptions.InvalidStringException;

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

    private void setName(String name) {
        if(name == null || name.equals("")) {
            throw new InvalidStringException();
        }
        this.name = name;
    }

    public void enrollStudent(Student student) {
        if(this.studentsByName.containsKey(student.getUserName())) {
            throw new DuplicateEntryInStructureException(student.getUserName(), this.name);
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
