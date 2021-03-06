package main.com.KaPrim.models;

import main.com.KaPrim.exceptions.DuplicateEntryInStructureException;
import main.com.KaPrim.exceptions.InvalidStringException;
import main.com.KaPrim.exceptions.KeyNotFoundException;
import main.com.KaPrim.staticData.ExceptionMessages;

import java.util.*;

public class StudentImpl implements Student {
    private String userName;
    private LinkedHashMap<String, Course> enrolledCourses;
    private LinkedHashMap<String, Double> marksByCourseName;

    public StudentImpl(String userName) {
        this.setUserName(userName);
        this.enrolledCourses = new LinkedHashMap<>();
        this.marksByCourseName = new LinkedHashMap<>();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        if (userName == null || userName.equals("")) {
            throw new InvalidStringException();
        }
        this.userName = userName;
    }

    @Override
    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(this.enrolledCourses);
    }

    @Override
    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(marksByCourseName);
    }

    @Override
    public void enrollInCourse(Course courseImpl) {
        if (this.enrolledCourses.containsKey(courseImpl.getName())) {
            throw new DuplicateEntryInStructureException(
                    this.userName, courseImpl.getName());
        }

        this.enrolledCourses.put(courseImpl.getName(), courseImpl);
    }

    @Override
    public void setMarkOnCourse(String courseName, int[] scores) {
        if (!this.enrolledCourses.containsKey(courseName)) {
            throw new KeyNotFoundException();
        }

        if (scores.length > CourseImpl.NUMBER_OF_TASKS_ON_EXAM) {
            throw new IllegalArgumentException(
                    ExceptionMessages.INVALID_NUMBER_OF_SCORES);
        }

        double mark = calculateMark(scores);
        this.marksByCourseName.put(courseName, mark);
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum() /
                (double) (CourseImpl.NUMBER_OF_TASKS_ON_EXAM * CourseImpl.MAX_SCORE_ON_EXAM_TASK);
        double mark = percentageOfSolvedExam * 4 + 2;
        return mark;
    }

    @Override
    public String getMarkForCourse(String courseName) {
        String output = String.format("%s - %f",
                this.userName, marksByCourseName.get(courseName));
        return output;
    }

    @Override
    public int compareTo(Student other) {
        return this.getUserName().compareTo(other.getUserName());
    }

    @Override
    public String toString() {
        return this.getUserName();
    }
}
