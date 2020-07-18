package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class AuthenticationHandler {

    private final PPCDatabaseManager dbManager;

    public AuthenticationHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login(HttpSession ses) {
        return new ModelAndView("log-in");
    }

    @PostMapping(value = {"/", "/login"})
    public ModelAndView login(HttpServletRequest req,
                              HttpServletResponse resp,
                              HttpSession ses,
                              @RequestParam String username,
                              @RequestParam String password) throws IOException, SQLException {
        if(ses.getAttribute("success") != null) ses.removeAttribute("success");
        ModelAndView ret = new ModelAndView("log-in");
        User user = dbManager.getUserByEmail(username);
        if(illegalCredentials(ret, user, username, password)) return ret;
        ses.setAttribute("user", user);
        resp.sendRedirect("/home");
        return null;
    }

    private boolean illegalCredentials(ModelAndView ret, User user, String username, String password) {
        if (user == null || !user.getPassword().equals(password)) {
            ret.addObject("error", "Incorrect username or password");
            ret.addObject("username", username);
            return true;
        }
        return false;
    }


    @RequestMapping("/logout")
    public void logout(HttpServletResponse resp, HttpSession ses) throws IOException {
        ses.invalidate();
        resp.sendRedirect("/log-in");
    }
}
