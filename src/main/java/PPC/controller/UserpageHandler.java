package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class UserpageHandler {

    private final PPCDatabaseManager dbManager;

    public UserpageHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping("/home")
    public ModelAndView home(HttpServletResponse resp, HttpSession ses) throws IOException {
        if (ses.getAttribute("user") == null) resp.sendRedirect("/logout");
        return new ModelAndView("user-page");
    }

    @PostMapping("/change-status")
    public void changeStatus(HttpSession ses, HttpServletResponse resp,
                             @RequestParam String email) throws SQLException, IOException {

        User admin = (User) ses.getAttribute("user");
        if (!admin.getStatus().equals(User.ADMINISTRATOR)) return;
        dbManager.setUserStatus(email, User.LECTURER);
        resp.sendRedirect("/home");
    }
}
