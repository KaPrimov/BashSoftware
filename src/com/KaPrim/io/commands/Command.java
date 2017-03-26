package com.KaPrim.io.commands;

import com.KaPrim.exceptions.InvalidInputException;
import com.KaPrim.io.IOManager;
import com.KaPrim.judge.Tester;
import com.KaPrim.network.DownloadManager;
import com.KaPrim.repository.StudentsRepository;

public abstract class Command {
    private String input;
    private String[] data;
    private StudentsRepository repository;
    private Tester tester;
    private IOManager ioManager;
    private DownloadManager downloadManager;

    protected Command(String input,
                      String[] data,
                      Tester tester,
                      StudentsRepository repository,
                      DownloadManager downloadManager,
                      IOManager ioManager) {
        this.setInput(input);
        this.setData(data);
        this.tester = tester;
        this.repository = repository;
        this.downloadManager = downloadManager;
        this.ioManager = ioManager;
    }

    protected StudentsRepository getRepository() {
        return repository;
    }

    protected Tester getTester() {
        return tester;
    }

    protected IOManager getIoManager() {
        return ioManager;
    }

    protected DownloadManager getDownloadManager() {
        return downloadManager;
    }

    protected String getInput() {
        return input;
    }

    private void setInput(String input) {
        if (input == null || input.equals("")) {
            throw new InvalidInputException(this.input);
        }
        this.input = input;
    }

    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidInputException(this.input);
        }
        this.data = data;
    }

    public abstract void execute() throws Exception;

}
