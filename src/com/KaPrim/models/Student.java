package com.KaPrim.models;

import com.KaPrim.exceptions.DuplicateEntryInStructureException;
import com.KaPrim.exceptions.InvalidStringException;
import com.KaPrim.staticData.ExceptionMessages;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
    private String userName;
    private Map<String, Course> enrolledCourses;
    private Map<String, Double> marksByCourseName;

    public Student(String userName) {
        setUserName(userName);
        this.enrolledCourses = new LinkedHashMap<>();
        this.marksByCourseName = new LinkedHashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName == null || userName.equals("")) {
            throw new InvalidStringException();
        }
        this.userName = userName;
    }

    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(this.marksByCourseName);
    }

    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(this.enrolledCourses);
    }

    public void enrollInCourse(Course course) {
        if(this.enrolledCourses.containsKey(course.getName())) {
           throw new DuplicateEntryInStructureException(this.getUserName(), course.getName());
        }

        this.enrolledCourses.put(course.getName(), course);
    }

    public void setMarksInCourse(String courseName, int... scores) {
        if(!this.enrolledCourses.containsKey(courseName)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_ENROLLED_IN_COURSE);

        }

        if(scores.length > Course.NUMBER_OF_TASKS_ON_EXAM) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);

        }

        double mark = calculateMark(scores);
        this.marksByCourseName.put(courseName, mark);
    }

    private double calculateMark(int[] scores) {
        double percentage = Arrays.stream(scores).sum() /
                (double) (Course.NUMBER_OF_TASKS_ON_EXAM * Course.MAX_SCORE_ON_EXAM_TASK);
        double mark = percentage * 4 + 2;
        return mark;
    }
}
