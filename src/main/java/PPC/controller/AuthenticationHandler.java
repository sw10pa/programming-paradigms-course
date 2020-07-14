package PPC.controller;

import java.io.*;
import java.sql.*;
import PPC.model.*;
import PPC.database.*;
import javax.servlet.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationHandler {

    private final PPCDatabaseManager dbManager;

    public AuthenticationHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping("/login")
    public String login() {
        return "log-in";
    }

    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest req,
                              HttpServletResponse resp,
                              HttpSession ses,
                              @RequestParam String username,
                              @RequestParam String password) throws IOException, SQLException {

        ModelAndView ret = new ModelAndView("log-in");
        Lecturer lecturer = dbManager.getLecturerByEmail(username);
        if (lecturer == null) {
            Student student = dbManager.getStudentByEmail(username);
            if (student == null || !student.getPassword().equals(password)) {
                ret.addObject("error", "Incorrect username or password");
                ret.addObject("username", username);
                return ret;
            }
            ses.setAttribute("student", student);
            resp.sendRedirect("/student-page");
        } else {
            if (!lecturer.getPassword().equals(password)) {
                ret.addObject("error", "Incorrect username or password");
                ret.addObject("username", username);
                return ret;
            }
            ses.setAttribute("lecturer", lecturer);
            resp.sendRedirect("/lecturer-page");
        }

        return ret;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletResponse resp, HttpSession ses) throws IOException {
        ses.invalidate();
        resp.sendRedirect("/log-in");
    }

}
