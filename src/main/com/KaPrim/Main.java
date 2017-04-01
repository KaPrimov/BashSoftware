package main.com.KaPrim;

import main.com.KaPrim.io.CommandInterpreter;
import main.com.KaPrim.io.IOManager;
import main.com.KaPrim.io.InputReader;
import main.com.KaPrim.io.OutputWriter;
import main.com.KaPrim.judge.Tester;
import main.com.KaPrim.network.DownloadManager;
import main.com.KaPrim.repository.RepositoryFilter;
import main.com.KaPrim.repository.RepositorySorter;
import main.com.KaPrim.repository.StudentsRepository;

public class Main {

    public static void main(String[] args) {
        Tester tester = new Tester();
        DownloadManager downloadManager = new DownloadManager();
        IOManager ioManager = new IOManager();
        RepositorySorter sorter = new RepositorySorter();
        RepositoryFilter filter = new RepositoryFilter();
        StudentsRepository repository = new StudentsRepository(filter, sorter);
        CommandInterpreter currentInterpreter = new CommandInterpreter(
                tester, repository, downloadManager, ioManager);
        InputReader reader = new InputReader(currentInterpreter);

        try {
            reader.readCommands();
        } catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}