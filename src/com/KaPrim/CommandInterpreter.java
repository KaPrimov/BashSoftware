package com.KaPrim;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CommandInterpreter {
    public static void interpretCommand(String input) {
        String[] data = input.split("\\s+");
        String command = data[0];

        switch (command) {
            case "open":
                tryOpenFile(input, data);
                break;
            case "mkdir":
                tryCreateDirectory(input, data);
                break;
            case "ls":
                tryTraverseDirectory(input, data);
                break;
            case "cmp":
                tryCompareTwoFile(input, data);
                break;
            case "changeDirRel":
                tryChangeRelativePath(input, data);
                break;
            case "changeDirAbs":
                tryChangeDirectoryAbsolute(input, data);
                break;
            case "readDb":
                tryReadDBFromFile(input, data);
                break;
            case "filter":
                tryPrintFilteredStudents(input, data);
                break;
            case "order":
                tryPrintOrderedStudents(input, data);
                break;
            case "download":
                break;
            case "downloadAsync":
                break;
            case "help":
                showHelp();
                break;
            case "show":
                tryShowWantedCourse(input, data);
                break;
            default:
                displayInvalidCommandMessage(input);
                break;
        }
    }

    private static void tryShowWantedCourse(String input, String[] data) {
        if (data.length != 2 && data.length != 3) {
            displayInvalidCommandMessage(input);
            return;
        }

        if(data.length == 2) {
            String courseName = data[1];
            StudentsRepository.getStudentsByCourse(courseName);
        }

        if (data.length == 3) {
            String cousrName = data[1];
            String userName = data[2];
            StudentsRepository.getStudentMarksInCourse(cousrName, userName);
        }
    }

    private static void showHelp() {
        StringBuilder helpList = new StringBuilder();
        helpList.append("Available commands:\n");
        helpList.append("mkdir directoryName? - make directory in the current directory.\n");
        helpList.append("ls (depth) traverse directory, optionally may give depth.\n");
        helpList.append("cmp absolutePath1 absolutePath2 – comparing two files by given two absolute paths.\n");
        helpList.append("changeDirRel relativePath – change current directory by a relative path.\n");
        helpList.append("changeDirAbs absolutePath – change current directory by an absolute path.\n");
        helpList.append("readDb dataBaseFileName – read students data base by given the name of the data base file \n");
        helpList.append("filter courseName poor/average/excellent take 2/10/42/all – filter students from some course by given filter and add quantity.\n");
        helpList.append("order courseName ascending/descending take 3/26/52/all – order student from given course by" +
                "ascending or descending order and then taking some quantity of the filter.\n");
        helpList.append("download (path of file) – download file.\n");
        helpList.append("downloadAsynch: (path of file) – download file asynchronously.\n");
        helpList.append("open – open file .\n");
        helpList.append("help – get help.\n");
        OutputWriter.writeMessage(helpList.toString());
    }

    private static void tryReadDBFromFile(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String fileName = data[1];
        StudentsRepository.initializeData(fileName);
    }

    private static void tryChangeDirectoryAbsolute(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String absolutePath = data[1];
        IOManager.changeCurrentDirAbsolute(absolutePath);
    }

    private static void tryChangeRelativePath(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String relativePath = data[1];
        IOManager.changeCurrentRelativePath(relativePath);
    }

    private static void tryCompareTwoFile(String input, String[] data) {
        if (data.length != 3) {
            displayInvalidCommandMessage(input);
            return;
        }

        String firstPath = data[1];
        String secondPath = data[2];
        Tester.compareContent(firstPath, secondPath);
    }

    private static void tryTraverseDirectory(String input, String[] data) {
        if(data.length != 1 && data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        if(data.length == 1) {
            Main.traverseDirectory(0);
        }

        if (data.length == 2) {
            Main.traverseDirectory(Integer.parseInt(data[1]));
        }
    }

    private static void tryCreateDirectory(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        String folderName = data[1];
        IOManager.createDirectoryInCurrentFolder(folderName);
    }

    private static void displayInvalidCommandMessage(String input) {
        String output = String.format("The command '%s' is invalid", input);
        OutputWriter.writeMessageOnNewLine(output);
    }

    private static void tryOpenFile(String input, String[] data)  {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String filename = data[1];
        String filePath = SessionData.currentPath + "\\" + filename;
        File file = new File(filePath);
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryPrintFilteredStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
            return;
        }

        String course = data[1];
        String filter = data[2];

        if (data.length == 3) {
            StudentsRepository.printFilteredStudents(course, filter, null);
            return;
        }

        Integer numberOfStudents = Integer.valueOf(data[3]);
        StudentsRepository.printFilteredStudents(course, filter, numberOfStudents);

    }

    private static void tryPrintOrderedStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
            return;
        }

        String course = data[1];
        String filter = data[2];

        if (data.length == 3) {
            StudentsRepository.printOrderedStudents(course, filter, null);
            return;
        }

        Integer numberOfStudents = Integer.valueOf(data[3]);
        StudentsRepository.printOrderedStudents(course, filter, numberOfStudents);

    }
}
