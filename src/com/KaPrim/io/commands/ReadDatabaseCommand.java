package com.KaPrim.io.commands;

import com.KaPrim.annotations.Alias;
import com.KaPrim.annotations.Inject;
import com.KaPrim.exceptions.InvalidInputException;
import com.KaPrim.repository.StudentsRepository;

@Alias("readdb")
public class ReadDatabaseCommand extends Command {

    @Inject
    private StudentsRepository repository;

    public ReadDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileName = data[1];
        this.repository.loadData(fileName);
    }
}
