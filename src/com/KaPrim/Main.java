package com.KaPrim;

import com.KaPrim.IO.InputReader;
import com.KaPrim.IO.OutputWriter;
import com.KaPrim.StaticData.SessionData;

import java.io.File;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        InputReader.readCommands();
    }
    public static void traverseDirectory(int depth) {
        LinkedList<File> subFolders = new LinkedList<>();

        String path = SessionData.currentPath;
        int initialIndentation = path.split("\\\\").length;

        File root = new File(path);

        subFolders.add(root);

        while (subFolders.size() != 0) {
            File currentFolder = subFolders.removeFirst();
            int currentIndentation = currentFolder.toString().split("\\\\").length - initialIndentation;

            if (depth - currentIndentation < 0) {
                break;
            }

            OutputWriter.writeMessageOnNewLine(currentFolder.toString());

            if (currentFolder.listFiles() != null ) {
                for (File file : currentFolder.listFiles()) {
                    if(file.isDirectory()) {
                        subFolders.add(file);
                    } else {
                        int indexLastSlash = file.toString().lastIndexOf("\\");
                        for (int i = 0; i < indexLastSlash; i++) {
                            OutputWriter.writeMessage("-");
                        }

                        OutputWriter.writeMessageOnNewLine(file.getName());
                    }
                }
            }
        }
    }
}
