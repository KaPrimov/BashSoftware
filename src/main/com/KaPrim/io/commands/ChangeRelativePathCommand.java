package main.com.KaPrim.io.commands;

import main.com.KaPrim.annotations.Alias;
import main.com.KaPrim.annotations.Inject;
import main.com.KaPrim.exceptions.InvalidInputException;
import main.com.KaPrim.io.IOManager;

@Alias("cdrel")
public class ChangeRelativePathCommand extends Command {

    @Inject
    private IOManager ioManager;

    public ChangeRelativePathCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String relativePath = data[1];
        this.ioManager.changeCurrentDirRelativePath(relativePath);
    }
}
