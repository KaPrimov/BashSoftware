package com.KaPrim.io.commands;

import com.KaPrim.annotations.Alias;
import com.KaPrim.annotations.Inject;
import com.KaPrim.exceptions.InvalidInputException;
import com.KaPrim.repository.StudentsRepository;

@Alias("show")
public class ShowCourseCommand extends Command {

    @Inject
    private StudentsRepository repository;

    public ShowCourseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2 && data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 2) {
            String courseName = data[1];
            this.repository.getStudentsByCourse(courseName);
            return;
        }

        String courseName = data[1];
        String userName = data[2];
        this.repository.getStudentMarkInCourse(courseName, userName);

    }
}
