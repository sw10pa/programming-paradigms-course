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
                     @RequestParam String currentPass,
                     @RequestParam String newPass) throws IOException, SQLException {
        User user = (User) ses.getAttribute("user");
        ModelAndView ret = new ModelAndView("change-password");
        currentPass = AuthenticationHandler.hashPassword(currentPass);
        if(!currentPass.equals(user.getPassword())){
            ret.addObject("error", "Wrong current password");
            return ret;
        }
        dbManager.setPassword(user.getEmail(), AuthenticationHandler.hashPassword(newPass));
        ses.setAttribute("success", "Password changed successfully");
        resp.sendRedirect("/");
        return null;
    }

}
