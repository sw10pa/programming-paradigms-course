package PPC.filesystem;

import java.io.*;

public class FileManager {

    public static void createFile(String path, String fileName) throws IOException {
        File file = new File(path + fileName);
        if (file.createNewFile()) {
            System.out.println("File created.");
        } else {
            System.out.println("File already exists.");
        }
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + fileName);
        if (file.delete()) {
            System.out.println("File deleted.");
        } else {
            System.out.println("File does not exist.");
        }
    }

}
