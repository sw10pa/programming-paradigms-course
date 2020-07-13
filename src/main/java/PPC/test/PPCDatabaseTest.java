package PPC.test;

import java.sql.*;
import PPC.model.*;
import PPC.database.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PPCDatabaseTest {

    private PPCDatabaseManager ppcDatabaseManager;

    @BeforeEach
    public void SetUp() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        ppcDatabaseManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @Test
    public void PPCDatabaseStudentsTest() throws SQLException {
        Student dchec18 = new Student(0, "Davit", "Chechelashvili", "dchec18@freeuni.edu.ge", "Davit13");
        Student sgurg18 = new Student(0, "Stepane", "Gurgenidze", "sgurg18@freeuni.edu.ge", "Stepane27");
        Student nadei18 = new Student(0, "Nikoloz", "Adeishvili", "nadei18@freeuni.edu.ge", "Nikoloz29");
        Student akvin18 = new Student(0, "Anastasia", "Kvinikadze", "akvin18@freeuni.edu.ge", "Anastasia10");

        ppcDatabaseManager.addStudent(dchec18);
        ppcDatabaseManager.addStudent(sgurg18);
        ppcDatabaseManager.addStudent(nadei18);
        ppcDatabaseManager.addStudent(akvin18);

        dchec18 = ppcDatabaseManager.getStudentByEmail("dchec18@freeuni.edu.ge");
        sgurg18 = ppcDatabaseManager.getStudentByEmail("sgurg18@freeuni.edu.ge");
        nadei18 = ppcDatabaseManager.getStudentByEmail("nadei18@freeuni.edu.ge");
        akvin18 = ppcDatabaseManager.getStudentByEmail("akvin18@freeuni.edu.ge");

        assertEquals(dchec18.getStudentId(), 1);
        assertEquals(sgurg18.getStudentId(), 2);
        assertEquals(nadei18.getStudentId(), 3);
        assertEquals(akvin18.getStudentId(), 4);

        assertEquals(dchec18.getFirstName(), "Davit");
        assertEquals(sgurg18.getFirstName(), "Stepane");
        assertEquals(nadei18.getFirstName(), "Nikoloz");
        assertEquals(akvin18.getFirstName(), "Anastasia");

        assertEquals(dchec18.getLastName(), "Chechelashvili");
        assertEquals(sgurg18.getLastName(), "Gurgenidze");
        assertEquals(nadei18.getLastName(), "Adeishvili");
        assertEquals(akvin18.getLastName(), "Kvinikadze");

        assertEquals(dchec18.getEmail(), "dchec18@freeuni.edu.ge");
        assertEquals(sgurg18.getEmail(), "sgurg18@freeuni.edu.ge");
        assertEquals(nadei18.getEmail(), "nadei18@freeuni.edu.ge");
        assertEquals(akvin18.getEmail(), "akvin18@freeuni.edu.ge");

        assertEquals(dchec18.getPassword(), "Davit13");
        assertEquals(sgurg18.getPassword(), "Stepane27");
        assertEquals(nadei18.getPassword(), "Nikoloz29");
        assertEquals(akvin18.getPassword(), "Anastasia10");

        ppcDatabaseManager.removeStudentByEmail("dchec18@freeuni.edu.ge");
        ppcDatabaseManager.removeStudentByEmail("sgurg18@freeuni.edu.ge");
        ppcDatabaseManager.removeStudentByEmail("nadei18@freeuni.edu.ge");
        ppcDatabaseManager.removeStudentByEmail("akvin18@freeuni.edu.ge");

        dchec18 = ppcDatabaseManager.getStudentByEmail("dchec18@freeuni.edu.ge");
        sgurg18 = ppcDatabaseManager.getStudentByEmail("sgurg18@freeuni.edu.ge");
        nadei18 = ppcDatabaseManager.getStudentByEmail("nadei18@freeuni.edu.ge");
        akvin18 = ppcDatabaseManager.getStudentByEmail("akvin18@freeuni.edu.ge");

        assertNull(dchec18);
        assertNull(sgurg18);
        assertNull(nadei18);
        assertNull(akvin18);
    }

    @Test
    public void PPCDatabaseLecturersTest() throws SQLException {
        Lecturer dchec18 = new Lecturer(0, "Davit", "Chechelashvili", "dchec18@freeuni.edu.ge", "Davit13");
        Lecturer sgurg18 = new Lecturer(0, "Stepane", "Gurgenidze", "sgurg18@freeuni.edu.ge", "Stepane27");
        Lecturer nadei18 = new Lecturer(0, "Nikoloz", "Adeishvili", "nadei18@freeuni.edu.ge", "Nikoloz29");
        Lecturer akvin18 = new Lecturer(0, "Anastasia", "Kvinikadze", "akvin18@freeuni.edu.ge", "Anastasia10");

        ppcDatabaseManager.addLecturer(dchec18);
        ppcDatabaseManager.addLecturer(sgurg18);
        ppcDatabaseManager.addLecturer(nadei18);
        ppcDatabaseManager.addLecturer(akvin18);

        dchec18 = ppcDatabaseManager.getLecturerByEmail("dchec18@freeuni.edu.ge");
        sgurg18 = ppcDatabaseManager.getLecturerByEmail("sgurg18@freeuni.edu.ge");
        nadei18 = ppcDatabaseManager.getLecturerByEmail("nadei18@freeuni.edu.ge");
        akvin18 = ppcDatabaseManager.getLecturerByEmail("akvin18@freeuni.edu.ge");

        assertEquals(dchec18.getLecturerId(), 1);
        assertEquals(sgurg18.getLecturerId(), 2);
        assertEquals(nadei18.getLecturerId(), 3);
        assertEquals(akvin18.getLecturerId(), 4);

        assertEquals(dchec18.getFirstName(), "Davit");
        assertEquals(sgurg18.getFirstName(), "Stepane");
        assertEquals(nadei18.getFirstName(), "Nikoloz");
        assertEquals(akvin18.getFirstName(), "Anastasia");

        assertEquals(dchec18.getLastName(), "Chechelashvili");
        assertEquals(sgurg18.getLastName(), "Gurgenidze");
        assertEquals(nadei18.getLastName(), "Adeishvili");
        assertEquals(akvin18.getLastName(), "Kvinikadze");

        assertEquals(dchec18.getEmail(), "dchec18@freeuni.edu.ge");
        assertEquals(sgurg18.getEmail(), "sgurg18@freeuni.edu.ge");
        assertEquals(nadei18.getEmail(), "nadei18@freeuni.edu.ge");
        assertEquals(akvin18.getEmail(), "akvin18@freeuni.edu.ge");

        assertEquals(dchec18.getPassword(), "Davit13");
        assertEquals(sgurg18.getPassword(), "Stepane27");
        assertEquals(nadei18.getPassword(), "Nikoloz29");
        assertEquals(akvin18.getPassword(), "Anastasia10");

        ppcDatabaseManager.removeLecturerByEmail("dchec18@freeuni.edu.ge");
        ppcDatabaseManager.removeLecturerByEmail("sgurg18@freeuni.edu.ge");
        ppcDatabaseManager.removeLecturerByEmail("nadei18@freeuni.edu.ge");
        ppcDatabaseManager.removeLecturerByEmail("akvin18@freeuni.edu.ge");

        dchec18 = ppcDatabaseManager.getLecturerByEmail("dchec18@freeuni.edu.ge");
        sgurg18 = ppcDatabaseManager.getLecturerByEmail("sgurg18@freeuni.edu.ge");
        nadei18 = ppcDatabaseManager.getLecturerByEmail("nadei18@freeuni.edu.ge");
        akvin18 = ppcDatabaseManager.getLecturerByEmail("akvin18@freeuni.edu.ge");

        assertNull(dchec18);
        assertNull(sgurg18);
        assertNull(nadei18);
        assertNull(akvin18);
    }

}
