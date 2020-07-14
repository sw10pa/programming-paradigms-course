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
        return "sign-up";
    }

    @PostMapping("/signup")
    public ModelAndView post(HttpServletResponse resp,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String username,
                             @RequestParam String password) throws IOException, SQLException {
        ModelAndView ret = new ModelAndView("sign-up");
        User user = dbManager.getUserByEmail(username);
        if (user != null) {
            ret.addObject("error", "Username " + username + " is already taken");
            ret.addObject("username", username);
            return ret;
        }
        dbManager.addUser(new User(firstName,lastName,username, password));
        resp.sendRedirect("/login");
        return null;
    }
}