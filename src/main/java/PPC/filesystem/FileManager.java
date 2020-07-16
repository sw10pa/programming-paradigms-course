package PPC.filesystem;

import java.io.*;

public class FileManager {

    public static void createFile(String path, String fileName) throws IOException {
        File file = new File(path + fileName);
        assert(file.createNewFile());
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + fileName);
        assert(file.delete());
    }

}
