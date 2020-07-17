package PPC.test;

import java.io.*;
import PPC.filesystem.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    @Test
    public void FileManagerFunctionsTest() throws IOException {
        String path = "src/main/java/PPC/test/";
        String fileName = "Begging.txt";
        String text = "Oh\n" +
                "Put your loving hand out, baby\n" +
                "I'm beggin'\n" +
                "Beggin', beggin' you\n" +
                "Put your loving hand out baby\n" +
                "Beggin', beggin' you\n" +
                "Put your loving hand out darling\n";

        FileManager.createFile(path, fileName);
        assertEquals("", FileManager.readFile(path, fileName));

        FileManager.writeToFile(path, fileName, text);
        assertEquals(text, FileManager.readFile(path, fileName));

        FileManager.writeToFile(path, fileName, "");
        assertEquals("", FileManager.readFile(path, fileName));

        FileManager.deleteFile(path, fileName);
    }

}
