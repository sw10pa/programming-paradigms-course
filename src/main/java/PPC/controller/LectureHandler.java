package PPC.controller;

import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LectureHandler {

    @GetMapping("/lecture")
    public ModelAndView get(HttpServletResponse resp, HttpSession ses, HttpServletRequest req) throws IOException {
        if (ses.getAttribute("user") == null) resp.sendRedirect("/logout");
        ModelAndView ret;

        User user = (User) ses.getAttribute("user");
        if (user.getStatus().equals(User.STUDENT))
            ret = new ModelAndView("lecture-page-lecturer");
        else
            ret = new ModelAndView("lecture-page-student");

        String lectureId = req.getParameter("lectureId");
        ret.addObject("lectureId", lectureId);

        return ret;
    }

}
