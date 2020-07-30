package PPC.test;

import java.io.*;
import java.sql.*;
import java.util.*;
import PPC.model.*;
import PPC.database.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PPCDatabaseTest {

    PPCDatabaseManager dbManager;

    @BeforeEach
    public void SetUp() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @Test
    public void AddLecturesTest() throws SQLException, IOException {
        for (int i = 1; i <= 32; i++) {
            dbManager.addLecture(new Lecture("Lecture " + i));
        }

        dbManager.setLectureVideoUrl(1, "https://youtu.be/Ps8jOj7diA0");
        dbManager.setLectureVideoUrl(2, "https://youtu.be/jTSvthW34GU");
        dbManager.setLectureVideoUrl(3, "https://youtu.be/H4MQXBF6FN4");
        dbManager.setLectureVideoUrl(4, "https://youtu.be/_eR4rxnM7Lc");
        dbManager.setLectureVideoUrl(5, "https://youtu.be/73Z7gaAvovQ");
        dbManager.setLectureVideoUrl(6, "https://youtu.be/iyLNYXcEtWE");
        dbManager.setLectureVideoUrl(7, "https://youtu.be/Yr1YnOVG-4g");
        dbManager.setLectureVideoUrl(8, "https://youtu.be/1nYDflSL0Mg");
        dbManager.setLectureVideoUrl(9, "https://youtu.be/arjo2-JQeaY");
        dbManager.setLectureVideoUrl(10, "https://youtu.be/FvpxXmEG1F8");
        dbManager.setLectureVideoUrl(11, "https://youtu.be/DwTXMjVkIUY");
        dbManager.setLectureVideoUrl(12, "https://youtu.be/0rXjvLa2NSs");
        dbManager.setLectureVideoUrl(13, "https://youtu.be/ucQI5HpiFrI");
        dbManager.setLectureVideoUrl(14, "https://youtu.be/TRfbJIsDBIM");
        dbManager.setLectureVideoUrl(15, "https://youtu.be/omE3YYpHhLo");
        dbManager.setLectureVideoUrl(16, "https://youtu.be/OGHN_zVTMMo");
        dbManager.setLectureVideoUrl(17, "https://youtu.be/kF3eSQTFagQ");
        dbManager.setLectureVideoUrl(18, "https://youtu.be/ynwh5O3jVRM");
        dbManager.setLectureVideoUrl(19, "https://youtu.be/_cV8NWQCxnE");
        dbManager.setLectureVideoUrl(20, "https://youtu.be/onKR7ICXacQ");
        dbManager.setLectureVideoUrl(21, "https://youtu.be/omzSd3En5g4");
        dbManager.setLectureVideoUrl(22, "https://youtu.be/3LeCydausnk");
        dbManager.setLectureVideoUrl(23, "https://youtu.be/TJkH1CSHg44");
        dbManager.setLectureVideoUrl(24, "https://youtu.be/_9XAlLofYwU");
        dbManager.setLectureVideoUrl(25, "https://youtu.be/V-5DCBQdErM");
        dbManager.setLectureVideoUrl(26, "https://youtu.be/PrnRTwCaWz8");
        dbManager.setLectureVideoUrl(27, "https://youtu.be/cXY4fSA7DnM");

        ArrayList<Lecture> lectures = dbManager.getAllLectures();
        assertEquals(32, lectures.size());

        for (int i = 1; i <= lectures.size(); i++) {
            Lecture lecture = lectures.get(i - 1);
            assertEquals(i, lecture.getLectureId());
            assertEquals("Lecture " + i, lecture.getLectureName());
            assertEquals("Lecture " + i + ".txt", lecture.getFileName());
        }
    }

    public void UsersTableTest() throws SQLException {
        User dchec = new User("Davit", "Chechelashvili", "dchec18@freeuni.edu.ge", "Davit13");
        User sgurg = new User("Stepane", "Gurgenidze", "sgurg18@freeuni.edu.ge", "Stepane27");
        User nadei = new User("Nikoloz", "Adeishvili", "nadei18@freeuni.edu.ge", "Nikoloz29");
        User akvin = new User("Anastasia", "Kvinikadze", "akvin18@freeuni.edu.ge", "Anastasia10");

        dbManager.addUser(dchec);
        dbManager.addUser(sgurg);
        dbManager.addUser(nadei);
        dbManager.addUser(akvin);

        dchec = dbManager.getUserByEmail("dchec18@freeuni.edu.ge");
        sgurg = dbManager.getUserByEmail("sgurg18@freeuni.edu.ge");
        nadei = dbManager.getUserByEmail("nadei18@freeuni.edu.ge");
        akvin = dbManager.getUserByEmail("akvin18@freeuni.edu.ge");

        assertEquals(1, dchec.getUserId());
        assertEquals(2, sgurg.getUserId());
        assertEquals(3, nadei.getUserId());
        assertEquals(4, akvin.getUserId());

        assertEquals("Davit", dchec.getFirstName());
        assertEquals("Stepane", sgurg.getFirstName());
        assertEquals("Nikoloz", nadei.getFirstName());
        assertEquals("Anastasia", akvin.getFirstName());

        assertEquals("Chechelashvili", dchec.getLastName());
        assertEquals("Gurgenidze", sgurg.getLastName());
        assertEquals("Adeishvili", nadei.getLastName());
        assertEquals("Kvinikadze", akvin.getLastName());

        assertEquals("dchec18@freeuni.edu.ge", dchec.getEmail());
        assertEquals("sgurg18@freeuni.edu.ge", sgurg.getEmail());
        assertEquals("nadei18@freeuni.edu.ge", nadei.getEmail());
        assertEquals("akvin18@freeuni.edu.ge", akvin.getEmail());

        assertEquals("Davit13", dchec.getPassword());
        assertEquals("Stepane27", sgurg.getPassword());
        assertEquals("Nikoloz29", nadei.getPassword());
        assertEquals("Anastasia10", akvin.getPassword());

        assertEquals("STU", dchec.getStatus());
        assertEquals("STU", sgurg.getStatus());
        assertEquals("STU", nadei.getStatus());
        assertEquals("STU", akvin.getStatus());

        dbManager.setUserStatus(sgurg.getEmail(), User.STUDENT);
        dbManager.setUserStatus(nadei.getEmail(), User.LECTURER);
        dbManager.setUserStatus(akvin.getEmail(), User.ADMINISTRATOR);

        dchec = dbManager.getUserByEmail("dchec18@freeuni.edu.ge");
        sgurg = dbManager.getUserByEmail("sgurg18@freeuni.edu.ge");
        nadei = dbManager.getUserByEmail("nadei18@freeuni.edu.ge");
        akvin = dbManager.getUserByEmail("akvin18@freeuni.edu.ge");

        assertEquals(User.STUDENT, dchec.getStatus());
        assertEquals(User.STUDENT, sgurg.getStatus());
        assertEquals(User.LECTURER, nadei.getStatus());
        assertEquals(User.ADMINISTRATOR, akvin.getStatus());

        dbManager.removeUserByEmail("dchec18@freeuni.edu.ge");
        dbManager.removeUserByEmail("sgurg18@freeuni.edu.ge");
        dbManager.removeUserByEmail("nadei18@freeuni.edu.ge");
        dbManager.removeUserByEmail("akvin18@freeuni.edu.ge");

        dchec = dbManager.getUserByEmail("dchec18@freeuni.edu.ge");
        sgurg = dbManager.getUserByEmail("sgurg18@freeuni.edu.ge");
        nadei = dbManager.getUserByEmail("nadei18@freeuni.edu.ge");
        akvin = dbManager.getUserByEmail("akvin18@freeuni.edu.ge");

        assertNull(dchec);
        assertNull(sgurg);
        assertNull(nadei);
        assertNull(akvin);
    }

    public void LecturesTableTest() throws SQLException, IOException {
        Lecture lecture1 = new Lecture("Lecture 1");
        Lecture lecture2 = new Lecture("Lecture 2");
        Lecture lecture3 = new Lecture("Lecture 3");
        Lecture lecture4 = new Lecture("Lecture 4");

        dbManager.addLecture(lecture1);
        dbManager.addLecture(lecture2);
        dbManager.addLecture(lecture3);
        dbManager.addLecture(lecture4);

        lecture1 = dbManager.getLectureByName("Lecture 1");
        lecture2 = dbManager.getLectureByName("Lecture 2");
        lecture3 = dbManager.getLectureByName("Lecture 3");
        lecture4 = dbManager.getLectureByName("Lecture 4");

        assertEquals(1, lecture1.getLectureId());
        assertEquals(2, lecture2.getLectureId());
        assertEquals(3, lecture3.getLectureId());
        assertEquals(4, lecture4.getLectureId());

        assertEquals("Lecture 1", lecture1.getLectureName());
        assertEquals("Lecture 2", lecture2.getLectureName());
        assertEquals("Lecture 3", lecture3.getLectureName());
        assertEquals("Lecture 4", lecture4.getLectureName());

        assertEquals("Lecture 1.txt", lecture1.getFileName());
        assertEquals("Lecture 2.txt", lecture2.getFileName());
        assertEquals("Lecture 3.txt", lecture3.getFileName());
        assertEquals("Lecture 4.txt", lecture4.getFileName());

        dbManager.removeLectureByName("Lecture 1");
        dbManager.removeLectureByName("Lecture 2");
        dbManager.removeLectureByName("Lecture 3");
        dbManager.removeLectureByName("Lecture 4");

        lecture1 = dbManager.getLectureByName("Lecture 1");
        lecture2 = dbManager.getLectureByName("Lecture 2");
        lecture3 = dbManager.getLectureByName("Lecture 3");
        lecture4 = dbManager.getLectureByName("Lecture 4");

        assertNull(lecture1);
        assertNull(lecture2);
        assertNull(lecture3);
        assertNull(lecture4);
    }

}
