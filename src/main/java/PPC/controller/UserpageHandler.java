package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class UserpageHandler {
    private final PPCDatabaseManager dbManager;

    public UserpageHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession ses) {
        return new ModelAndView("user-page");
    }

}
