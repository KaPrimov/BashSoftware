package com.KaPrim.models;

import com.KaPrim.exceptions.DuplicateEntryInStructureException;
import com.KaPrim.exceptions.InvalidStringException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CourseImpl implements Course {

    public static final int NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final int MAX_SCORE_ON_EXAM_TASK = 100;

    private String name;
    private LinkedHashMap<String, Student> studentsByName;

    public CourseImpl(String name) {
        this.setName(name);
        this.studentsByName = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new InvalidStringException();
        }
        this.name = name;
    }

    @Override
    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(this.studentsByName);
    }

    @Override
    public void enrollStudent(Student studentImpl) {
        if (this.studentsByName.containsKey(studentImpl.getUserName())) {
            throw new DuplicateEntryInStructureException(
                    studentImpl.getUserName(), this.name);
        }

        this.studentsByName.put(studentImpl.getUserName(), studentImpl);
    }

    @Override
    public int compareTo(Course other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
