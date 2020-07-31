package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Controller
public class RegistrationHandler {

    private final PPCDatabaseManager dbManager;

    public RegistrationHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }


    @GetMapping("/signup")
    public void get(HttpServletResponse resp, HttpSession ses) throws IOException {
        ses.setAttribute("type", "registration");
        resp.sendRedirect("/");
    }

    @PostMapping("/signup")
    public void post(HttpServletRequest req,
                     HttpServletResponse resp,
                     @RequestParam String firstName,
                     @RequestParam String lastName,
                     @RequestParam String username,
                     @RequestParam String password) throws IOException, SQLException{

        User user = dbManager.getUserByEmail(username);

        HttpSession ses = req.getSession();
        if (illegalCredentials(ses, user, firstName, lastName, username, password)) {
            ses.setAttribute("type", "registration");
        } else {
            ses.setAttribute("success", "Registration completed successfully");
            dbManager.addUser(new User(firstName, lastName, username, AuthenticationHandler.hashPassword(password)));
        }
        resp.sendRedirect("/");
    }

    private boolean illegalCredentials(HttpSession ses,
                                       User user,
                                       String firstName,
                                       String lastName,
                                       String username,
                                       String password) {

        if (firstName.length() == 0 || lastName.length() == 0 ||
                username.length() == 0 || password.length() == 0) {
            ses.setAttribute("error", "Please fill every field");
            return true;
        }

        if (user != null) {
            ses.setAttribute("error", "Username is already taken");
            return true;
        }

        return false;
    }
}