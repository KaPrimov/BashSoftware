package com.KaPrim;

import java.util.*;

public class RepositorySorters {
    public static void printSortedStudents(
            HashMap<String, ArrayList<Integer>> courseData,
            String comparisonType,
            int numberOfStudents) {
        Comparator<Map.Entry<String, ArrayList<Integer>>> comparator = createComparator(comparisonType);

        ArrayList<Map.Entry<String, ArrayList<Integer>>> sortedStudents = new ArrayList<>();
        sortedStudents.addAll(courseData.entrySet());

        Collections.sort(sortedStudents, comparator);

        for (Map.Entry<String, ArrayList<Integer>> student : sortedStudents) {
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }

    private static Comparator<Map.Entry<String, ArrayList<Integer>>> createComparator(String comparisonType) {
        switch (comparisonType) {
            case "ascending":
                return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
                    @Override
                    public int compare(Map.Entry<String, ArrayList<Integer>> student1, Map.Entry<String, ArrayList<Integer>> student2) {
                        Double firstStudentTotal = getTotalScore(student1.getValue());
                        Double secondStudentTotal = getTotalScore(student2.getValue());

                        return firstStudentTotal.compareTo(secondStudentTotal);
                    }
                };
            case "descending":
                return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
                    @Override
                    public int compare(Map.Entry<String, ArrayList<Integer>> student1, Map.Entry<String, ArrayList<Integer>> student2) {
                        Double firstStudentTotal = getTotalScore(student1.getValue());
                        Double secondStudentTotal = getTotalScore(student2.getValue());

                        return secondStudentTotal.compareTo(firstStudentTotal);
                    }
                };
            default:
                return null;
        }
    }

    private static Double getTotalScore(List<Integer> grades) {
        Double totalScore = 0.0;

        for (Integer grade : grades) {
            totalScore+=grade;
        }

        return totalScore / grades.size();
    }
}
