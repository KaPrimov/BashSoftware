package main.com.KaPrim.io.commands;

import main.com.KaPrim.annotations.Alias;
import main.com.KaPrim.annotations.Inject;
import main.com.KaPrim.exceptions.InvalidInputException;
import main.com.KaPrim.io.OutputWriter;
import main.com.KaPrim.repository.StudentsRepository;

;

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
