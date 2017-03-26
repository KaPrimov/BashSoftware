package com.KaPrim.io.commands;

import com.KaPrim.dataStructures.SimpleSortedList;
import com.KaPrim.exceptions.InvalidInputException;
import com.KaPrim.io.IOManager;
import com.KaPrim.io.OutputWriter;
import com.KaPrim.judge.Tester;
import com.KaPrim.models.Course;
import com.KaPrim.models.Student;
import com.KaPrim.network.DownloadManager;
import com.KaPrim.repository.StudentsRepository;

import java.util.Comparator;

public class DisplayCommand extends Command {

    public DisplayCommand(String input, String[] data, Tester tester, StudentsRepository repository, DownloadManager downloadManager, IOManager ioManager) {
        super(input, data, tester, repository, downloadManager, ioManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if(data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        String entityDisplay = data[1];
        String sortType = data[2];

        if(entityDisplay.equalsIgnoreCase("students")) {
            Comparator<Student> studentComparator = this.createStudentComparator(sortType);
            SimpleSortedList<Student> list = this.getRepository().getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(
                    list.joinWith(System.lineSeparator())
            );
        } else if (entityDisplay.equalsIgnoreCase("courses")) {
            Comparator<Course> courseComparator = this.createCourseComparator(sortType);
            SimpleSortedList<Course> list = this.getRepository().getAllCoursesSorted(courseComparator);
            OutputWriter.writeMessageOnNewLine(
                    list.joinWith(System.lineSeparator())
            );
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Course> createCourseComparator(String sortType) {
        if(sortType.equalsIgnoreCase("ascending")) {
            return Comparable::compareTo;
        } else if (sortType.equalsIgnoreCase("descending")) {
            return Comparator.reverseOrder();
        } else {
            throw new  InvalidInputException(this.getInput());
        }
    }

    private Comparator<Student> createStudentComparator(String sortType) {
        if(sortType.equalsIgnoreCase("ascending")) {
            return Comparable::compareTo;
        } else if (sortType.equalsIgnoreCase("descending")) {
            return Comparator.reverseOrder();
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }
}
