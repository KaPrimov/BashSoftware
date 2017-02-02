package com.KaPrim;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentsRepository {
    public static boolean isDataInitialized = false;
    public static HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;

    public static void initializeData(String fileName) {
        if(isDataInitialized) {
            System.out.println(ExseptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }
        studentsByCourse = new HashMap<>();
        readData(fileName);
    }

    public static void readData(String fileName) {
        String regex = "([A-Z][A-Za-z#+]*_[A-Z][a-z]{2}_201[4-7])\\s+([A-Z][a-z]{0,3}\\d{2}_\\d{2,4})\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        String path = SessionData.currentPath + "\\" + fileName;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            matcher = pattern.matcher(line);
            if (!line.isEmpty() && matcher.find()) {
                String course = matcher.group(1);
                String student = matcher.group(2);
                int mark = Integer.parseInt(matcher.group(3));
                if (mark >= 0 && mark <= 100) {


                    if (!studentsByCourse.containsKey(course)) {
                        studentsByCourse.put(course, new HashMap<>());
                    }

                    if (!studentsByCourse.get(course).containsKey(student)) {
                        studentsByCourse.get(course).put(student, new ArrayList<>());
                    }

                    studentsByCourse.get(course).get(student).add(mark);
                }
            }
        }
        isDataInitialized = true;
        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    private static boolean isQueryForCoursePossible(String courseName) {
        if(!isDataInitialized) {
            OutputWriter.displayException(ExseptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }

        if(!studentsByCourse.containsKey(courseName)) {
            OutputWriter.displayException(ExseptionMessages.NON_EXISTING_COURSE);
        }
        return true;
    }

    public static boolean isQueryForStudentPossible(String courseName, String studentName) {
        if(!isQueryForCoursePossible(courseName)) {
            return false;
        }

        if(!studentsByCourse.get(courseName).containsKey(studentName)) {
            OutputWriter.displayException(ExseptionMessages.NON_EXISTING_STUDENT);
            return false;
        }
        return true;
    }

    public static void getStudentMarksInCourse(String course, String student) {
        if(!isQueryForStudentPossible(course, student)) {
            return;
        }

        ArrayList<Integer> marks = studentsByCourse.get(course).get(student);
        OutputWriter.printStudent(student, marks);
    }

    public static void getStudentsByCourse(String course) {
        if(!isQueryForCoursePossible(course)) {
            return;
        }

        OutputWriter.writeMessageOnNewLine(course + ":");
        for(Map.Entry<String, ArrayList<Integer>> student: studentsByCourse.get(course).entrySet()) {
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }
}






















