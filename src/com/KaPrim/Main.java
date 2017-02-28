package com.KaPrim;

import com.KaPrim.io.CommandInterpreter;
import com.KaPrim.io.IOManager;
import com.KaPrim.io.InputReader;
import com.KaPrim.io.OutputWriter;
import com.KaPrim.judge.Tester;
import com.KaPrim.network.DownloadManager;
import com.KaPrim.repository.RepositoryFilter;
import com.KaPrim.repository.RepositorySorter;
import com.KaPrim.repository.StudentsRepository;

public class Main {

    public static void main(String[] args) {
        Tester tester = new Tester();
        DownloadManager downloadManager = new DownloadManager();
        IOManager ioManager = new IOManager();
        RepositorySorter sorter = new RepositorySorter();
        RepositoryFilter filter = new RepositoryFilter();
        StudentsRepository repository = new StudentsRepository(filter, sorter);
        CommandInterpreter commandInterpreter =
                new CommandInterpreter(tester, repository,downloadManager, ioManager);
        InputReader reader = new InputReader(commandInterpreter);

        try {
            reader.readCommands();
        } catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}
