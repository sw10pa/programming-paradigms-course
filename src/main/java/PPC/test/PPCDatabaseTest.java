package PPC.test;

import java.sql.*;
import PPC.database.*;
import PPC.model.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PPCDatabaseTest {

    @Test
    public void PPCDatabaseManagerTest() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        PPCDatabaseManager dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());

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

}
