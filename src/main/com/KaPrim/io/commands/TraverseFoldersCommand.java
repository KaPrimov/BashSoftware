package main.com.KaPrim.io.commands;

import main.com.KaPrim.annotations.Alias;
import main.com.KaPrim.annotations.Inject;
import main.com.KaPrim.exceptions.InvalidInputException;
import main.com.KaPrim.io.IOManager;


@Alias("ls")
public class TraverseFoldersCommand extends Command {

    @Inject
    private IOManager ioManager;

    public TraverseFoldersCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1 && data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 1) {
            this.ioManager.traverseDirectory(0);
            return;
        }

        this.ioManager.traverseDirectory(Integer.valueOf(data[1]));
    }
}
