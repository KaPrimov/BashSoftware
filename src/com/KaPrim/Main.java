package com.KaPrim;

import java.io.File;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
//        String test1Path = "E:\\Programming\\JavaAdv-Tasks\\BashSoft\\src\\resources\\test1.txt";
//        String test2Path = "E:\\Programming\\JavaAdv-Tasks\\BashSoft\\src\\resources\\test3.txt";
//        Tester.compareContent(test1Path, test2Path);
//        System.out.println(SessionData.currentPath);
//        IOManager.changeCurrentRelativePath("..");
//        IOManager.changeCurrentRelativePath("..");
//        IOManager.changeCurrentRelativePath("..");
//        IOManager.changeCurrentRelativePath("Movies");
//        System.out.println(SessionData.currentPath);
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
