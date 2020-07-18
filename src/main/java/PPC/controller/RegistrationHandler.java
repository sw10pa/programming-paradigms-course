package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class RegistrationHandler {

    private final PPCDatabaseManager dbManager;

    public RegistrationHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }


    @GetMapping("/signup")
    public String get() {
        return "log-in";
    }

    @PostMapping("/signup")
    public ModelAndView post(HttpServletRequest req,
                             HttpServletResponse resp,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String username,
                             @RequestParam String password) throws IOException, SQLException {

        ModelAndView ret = new ModelAndView("log-in");
        User user = dbManager.getUserByEmail(username);

        //might need to use req.setAttribute
        if (illegalCredentials(ret, user, firstName, lastName, username, password)) return ret;
        ret.addObject("success", "Registration completed successfully");

        dbManager.addUser(new User(firstName, lastName, username, password));
        resp.sendRedirect("/login");
        return ret;
    }

    private boolean illegalCredentials(ModelAndView ret,
                                       User user,
                                       String firstName,
                                       String lastName,
                                       String username,
                                       String password) {

        if (firstName.length() == 0 || lastName.length() == 0 ||
                username.length() == 0 || password.length() == 0) {
            ret.addObject("error", "Please fill every field");
            return true;
        }

        if (user != null) {
            ret.addObject("error", "Username " + username + " is already taken");
            ret.addObject("username", username);
            return true;
        }

        return false;
    }
}