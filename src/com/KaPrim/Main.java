package com.KaPrim;

import java.io.File;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

    }
    public static void traverseDirectory(String path) {
        LinkedList<File> subFolders = new LinkedList<>();
        File root = new File(path);

        subFolders.add(root);

        while (subFolders.size() != 0) {
            File currenFolder = subFolders.removeFirst();
            try {
                if(currenFolder.listFiles() != null) {
                    for (File file: currenFolder.listFiles()) {
                        if (file.isDirectory()) {
                            subFolders.add(file);
                        }
                    }
                }
                System.out.println(currenFolder.toString());
            } catch (Exception e) {
                System.out.println("Access denied!");
            }

        }
    }
}
