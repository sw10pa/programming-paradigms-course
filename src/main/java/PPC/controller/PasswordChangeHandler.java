package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@Controller
public class PasswordChangeHandler {

    private final PPCDatabaseManager dbManager;

    public PasswordChangeHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }



}
