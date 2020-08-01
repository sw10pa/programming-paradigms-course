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
    public ModelAndView get() {
        return new ModelAndView("change-password");
    }

    @PostMapping("/change-password")
    public void post(HttpSession ses, HttpServletResponse resp,
                     @RequestParam String newPassword) throws IOException {
        User user = (User) ses.getAttribute("user");
        //dbManager.setPassword(user, AuthenticationHandler.hashPassword(newPassword));
        ses.setAttribute("success", "Password changed successfully");
        resp.sendRedirect("/");
    }


}
