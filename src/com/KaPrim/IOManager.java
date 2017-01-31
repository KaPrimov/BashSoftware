package com.KaPrim;

import java.io.File;

public class IOManager {
    public static void createDirectoryInCurrentFolder (String name) {
        String path = getCurrentDirectoryPath() + "\\" + name;
        File file = new File(path);
        file.mkdir();
    }

    public static String getCurrentDirectoryPath() {
        return SessionData.currentPath;
    }

    public static void changeCurrentRelativePath(String relativePath) {
        if (relativePath.equals("..")) {
            String currentPath = SessionData.currentPath;
            int indexOfLastSlash = currentPath.lastIndexOf("\\");
            String newPath = currentPath.substring(0, indexOfLastSlash);
            SessionData.currentPath = newPath;
        } else {
            String currentPath = SessionData.currentPath;
            currentPath += "\\" + relativePath;
            changeCurrentDirAbsolute(currentPath);
        }
    }

    private static void changeCurrentDirAbsolute(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            OutputWriter.displayException(ExseptionMessages.INVALID_PATH);
            return;
        }
        SessionData.currentPath = absolutePath;
    }
}
