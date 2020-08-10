package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class PasswordChangeHandler {

    private final PPCDatabaseManager dbManager;

    public PasswordChangeHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping("/change-password")
    public ModelAndView get(HttpSession ses) {
        ModelAndView ret = new ModelAndView("change-password");

        if (ses.getAttribute("error") != null) {
            ret.addObject("error", ses.getAttribute("error"));
            ses.removeAttribute("error");
        }

        if (ses.getAttribute("success") != null) {
            ret.addObject("success", ses.getAttribute("success"));
            ses.removeAttribute("success");
        }

        return ret;
    }

    @PostMapping("/change-password")
    public ModelAndView post(HttpSession ses, HttpServletResponse resp,
                             @RequestParam String currPassword,
                             @RequestParam String newPassword) throws IOException, SQLException {
        User user = (User) ses.getAttribute("user");
        ModelAndView ret = new ModelAndView("change-password");
        currPassword = AuthenticationHandler.hashPassword(currPassword);
        if (illegalCredentials(user, currPassword, newPassword, ret)) return ret;
        dbManager.setPassword(user.getEmail(), AuthenticationHandler.hashPassword(newPassword));
        ses.setAttribute("success", "Password changed successfully");
        resp.sendRedirect("/");
        return null;
    }

    private boolean illegalCredentials(User user,
                                       String currPassword,
                                       String newPassword,
                                       ModelAndView ret) {
        if (newPassword.length() == 0) {
            ret.addObject("error", "New password cannot be empty");
            return true;
        }
        if (!currPassword.equals(user.getPassword())) {
            ret.addObject("error", "Wrong current password");
            return true;
        }
        return false;
    }

    @GetMapping("/reset-password")
    public ModelAndView loadResetPage() {
        return new ModelAndView("reset-password");
    }

    @PostMapping("/reset-password")
    public ModelAndView resetPassword(HttpServletResponse resp, HttpSession ses, @RequestParam String username) throws SQLException, IOException {
        User user = dbManager.getUserByEmail(username);
        if (user == null) {
            ModelAndView ret = new ModelAndView("reset-password");
            ret.addObject("error", "Username doesn't exist");
            return ret;
        }
        String newPassword = RegistrationHandler.sendRandomPasswordToEmail(username);
        dbManager.setPassword(username, AuthenticationHandler.hashPassword(newPassword));
        ses.setAttribute("success", "Password reset successfully. Check your Email for new password");
        resp.sendRedirect("/");
        return null;
    }

}
