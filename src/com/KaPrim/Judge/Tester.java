package com.KaPrim.Judge;

import com.KaPrim.IO.OutputWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static void compareContent(String actualOutput, String expectedOutput) {
        OutputWriter.writeMessageOnNewLine("Reading files...");
        String mismatchPath = getMismatchPath(expectedOutput);
        List<String> actualOutputString = readTextFile(actualOutput);
        List<String> expectedOutputString = readTextFile(expectedOutput);
        boolean mismatch = compareStrings(actualOutputString, expectedOutputString, mismatchPath);

        if(mismatch) {
            List<String> mismatchString = readTextFile(mismatchPath);
            mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
        } else {
            OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches");
        }
    }

    private static boolean compareStrings (
            List<String> actualOutputString,
            List<String> expectedOutputString,
            String mismatchPath) {

        OutputWriter.writeMessageOnNewLine("Comparing files...");
        String output = "";
        boolean isMismatch = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mismatchPath))) {
            for (int i = 0; i < expectedOutputString.size(); i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);

                if(!actualLine.equals(expectedLine)) {
                    output = String.format("mismatch -> expected(%s), actual(%s)%n", expectedLine, actualLine);
                    isMismatch = true;
                } else {
                    output = String.format("line match -> %s%n", actualLine);
                }

                writer.write(output);
            }
        } catch (IOException e) {
            OutputWriter.displayException("Error");
        }

        return isMismatch;
    }

    public static String getMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return  directoryPath + "\\mismatch.txt";
    }

    private static List<String> readTextFile(String filePath) {
        List<String> text = new ArrayList<>();

        File file = new File(filePath);

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                text.add(line);
            }
        } catch (IOException e) {
            OutputWriter.writeMessageOnNewLine("File not found.");
        }

        return text;
    }
}
