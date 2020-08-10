package PPC.test;

import java.io.*;
import java.sql.*;
import java.util.*;

import PPC.model.*;
import PPC.database.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LecturesTableTest {

    private final static int LECTURES_COUNT = 32;

    private PPCDatabaseManager dbManager;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @Test
    public void addLecturesTest() throws SQLException, IOException {
        for (int i = 1; i <= LECTURES_COUNT; i++) {
            dbManager.addLecture(new Lecture("Lecture " + i));
        }

        ArrayList<Lecture> lectures = dbManager.getAllLectures();
        assertEquals(LECTURES_COUNT, lectures.size());

        for (int i = 1; i <= lectures.size(); i++) {
            Lecture lecture = lectures.get(i - 1);
            assertEquals(i, lecture.getLectureId());
            assertEquals("Lecture " + i, lecture.getLectureName());
            assertEquals("Lecture " + i + ".txt", lecture.getFileName());
        }

        dbManager.setLectureVideoUrl(1, "https://www.youtube.com/embed/Ps8jOj7diA0");
        dbManager.setLectureVideoUrl(2, "https://www.youtube.com/embed/jTSvthW34GU");
        dbManager.setLectureVideoUrl(3, "https://www.youtube.com/embed/H4MQXBF6FN4");
        dbManager.setLectureVideoUrl(4, "https://www.youtube.com/embed/_eR4rxnM7Lc");
        dbManager.setLectureVideoUrl(5, "https://www.youtube.com/embed/73Z7gaAvovQ");
        dbManager.setLectureVideoUrl(6, "https://www.youtube.com/embed/iyLNYXcEtWE");
        dbManager.setLectureVideoUrl(7, "https://www.youtube.com/embed/Yr1YnOVG-4g");
        dbManager.setLectureVideoUrl(8, "https://www.youtube.com/embed/1nYDflSL0Mg");
        dbManager.setLectureVideoUrl(9, "https://www.youtube.com/embed/arjo2-JQeaY");
        dbManager.setLectureVideoUrl(10, "https://www.youtube.com/embed/FvpxXmEG1F8");
        dbManager.setLectureVideoUrl(11, "https://www.youtube.com/embed/DwTXMjVkIUY");
        dbManager.setLectureVideoUrl(12, "https://www.youtube.com/embed/0rXjvLa2NSs");
        dbManager.setLectureVideoUrl(13, "https://www.youtube.com/embed/ucQI5HpiFrI");
        dbManager.setLectureVideoUrl(14, "https://www.youtube.com/embed/TRfbJIsDBIM");
        dbManager.setLectureVideoUrl(15, "https://www.youtube.com/embed/omE3YYpHhLo");
        dbManager.setLectureVideoUrl(16, "https://www.youtube.com/embed/OGHN_zVTMMo");
        dbManager.setLectureVideoUrl(17, "https://www.youtube.com/embed/kF3eSQTFagQ");
        dbManager.setLectureVideoUrl(18, "https://www.youtube.com/embed/ynwh5O3jVRM");
        dbManager.setLectureVideoUrl(19, "https://www.youtube.com/embed/_cV8NWQCxnE");
        dbManager.setLectureVideoUrl(20, "https://www.youtube.com/embed/onKR7ICXacQ");
        dbManager.setLectureVideoUrl(21, "https://www.youtube.com/embed/omzSd3En5g4");
        dbManager.setLectureVideoUrl(22, "https://www.youtube.com/embed/3LeCydausnk");
        dbManager.setLectureVideoUrl(23, "https://www.youtube.com/embed/TJkH1CSHg44");
        dbManager.setLectureVideoUrl(24, "https://www.youtube.com/embed/_9XAlLofYwU");
        dbManager.setLectureVideoUrl(25, "https://www.youtube.com/embed/V-5DCBQdErM");
        dbManager.setLectureVideoUrl(26, "https://www.youtube.com/embed/PrnRTwCaWz8");
        dbManager.setLectureVideoUrl(27, "https://www.youtube.com/embed/cXY4fSA7DnM");
    }

}
