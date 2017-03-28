package com.KaPrim.io.commands;

import com.KaPrim.annotations.Alias;
import com.KaPrim.annotations.Inject;
import com.KaPrim.exceptions.InvalidInputException;
import com.KaPrim.io.OutputWriter;
import com.KaPrim.repository.StudentsRepository;

@Alias("dropdb")
public class DropDatabaseCommand extends Command {

    @Inject
    private StudentsRepository studentsRepository;

    public DropDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1) {
            throw new InvalidInputException(this.getInput());
        }

        this.studentsRepository.unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }
}
