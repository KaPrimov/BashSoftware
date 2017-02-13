package com.KaPrim.Repository;

import com.KaPrim.StaticData.ExseptionMessages;
import com.KaPrim.IO.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class RepositoryFilters {

    public static void printFilteredStudents(
            HashMap<String, ArrayList<Integer>> courseData,
            String filterType,
            Integer numberOfStudents) {

        Predicate<Double> filter = createFilter(filterType);
        if (filter == null) {
            OutputWriter.writeMessageOnNewLine(ExseptionMessages.INVALID_FILTER);
            return;
        }

        filterAndTake(courseData, filter, numberOfStudents);
    }

    private static void filterAndTake(HashMap<String, ArrayList<Integer>> courseData, Predicate<Double> filter, Integer numberOfStudents) {
        int studentsCount = 0;
        for (String student : courseData.keySet()) {
            if (studentsCount >= numberOfStudents) {
                break;
            }
            List<Integer> studentsMarks = courseData.get(student);
            Double averageMark = studentsMarks
                    .stream()
                    .mapToInt(Integer::valueOf)
                    .average()
                    .getAsDouble();

            Double percentageOfFulfilment = averageMark / 100;
            Double mark = percentageOfFulfilment * 4 + 2;
            if (filter.test(averageMark)) {
                OutputWriter.printStudent(student, studentsMarks);
                studentsCount++;
            }
        }
    }

    private static Predicate<Double> createFilter(String filterType) {
        switch (filterType) {
            case "excellent":
                return mark -> mark >= 5.0;
            case "average":
                return mark -> 3.5 <= mark && mark < 5.0;
            case "poor":
                return mark -> mark < 3.5;
            default:
                return null;
        }
    }
}
