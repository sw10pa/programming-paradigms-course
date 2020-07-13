package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.Lecturer;
import PPC.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class AuthenticationController {

    private PPCDatabaseManager dbManager;

    public AuthenticationController() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "log-in";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
        }else{
            if(!lecturer.getPassword().equals(password)){
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
