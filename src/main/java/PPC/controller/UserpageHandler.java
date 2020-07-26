package PPC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserpageHandler {

    @GetMapping("/home")
    public ModelAndView home(HttpSession ses) {
        return new ModelAndView("user-page");
    }

}
