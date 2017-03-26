package bg.softuni.io.commands;

import bg.softuni.dataStructures.SimpleSortedList;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.IOManager;
import bg.softuni.io.OutputWriter;
import bg.softuni.judge.Tester;
import bg.softuni.models.Course;
import bg.softuni.models.Student;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

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
