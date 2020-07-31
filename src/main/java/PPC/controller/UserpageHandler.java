package PPC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserpageHandler {

    @GetMapping("/home")
    public ModelAndView home(HttpServletResponse resp, HttpSession ses) throws IOException {
        if (ses.getAttribute("user") == null) resp.sendRedirect("/logout");
        return new ModelAndView("user-page");
    }

}
