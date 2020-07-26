package PPC.controller;

import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LectureHandler {

    @GetMapping("/lecture")
    public ModelAndView get(HttpSession ses, HttpServletRequest req) {
        ModelAndView ret;

        User user = (User) ses.getAttribute("user");
        if (user.getStatus().equals(User.STUDENT))
            ret = new ModelAndView("lecture-page-student");
        else
            ret = new ModelAndView("lecture-page-lecturer");

        String lectureId = req.getParameter("lectureId");
        ret.addObject("lectureId", lectureId);

        return ret;
    }

}
