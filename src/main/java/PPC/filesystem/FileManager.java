package PPC.filesystem;

import java.io.*;
import java.util.*;

public class FileManager {

    public static void createFile(String path, String fileName) throws IOException {
        File file = new File(path + fileName);
        assert(file.createNewFile());
    }

    public static void writeToFile(String path, String fileName, String text) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + fileName));
        StringTokenizer stringTokenizer = new StringTokenizer(text, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            bufferedWriter.write(stringTokenizer.nextToken());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static String readFile(String path, String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path + fileName));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
            stringBuilder.append("\n");
        }
        scanner.close();
        return stringBuilder.toString();
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + fileName);
        assert(file.delete());
    }

}
