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

    public static void writeQuestionToFile(String path, String fileName, int rightAnswerIndex, ArrayList<String> questionStructure) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + fileName));
        bufferedWriter.write(Integer.toString(rightAnswerIndex));
        for (String s : questionStructure) {
            bufferedWriter.newLine();
            bufferedWriter.write(s);
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

    public static int readRightAnswerIndex(String path, String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path + fileName));
        int rightAnswerIndex = Integer.parseInt(scanner.nextLine());
        scanner.close();
        return rightAnswerIndex;
    }

    public static ArrayList<String> readQuestionStructure(String path, String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path + fileName));
        ArrayList<String> questionStructure = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            questionStructure.add(scanner.nextLine());
        }
        scanner.close();
        return questionStructure;
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + fileName);
        assert(file.delete());
    }

}
