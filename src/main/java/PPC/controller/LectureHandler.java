package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.filesystem.FileManager;
import PPC.model.Lecture;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class LectureHandler {

    private final PPCDatabaseManager dbManager;

    public LectureHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping("/lecture")
    public ModelAndView get(HttpServletResponse resp, HttpSession ses, HttpServletRequest req) throws IOException {
        if (ses.getAttribute("user") == null) resp.sendRedirect("/logout");
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

    @PostMapping("/edit-link")
    public void editVideoUrl(HttpServletResponse resp,
                             @RequestParam String lectureId,
                             @RequestParam String videoURL) throws IOException, SQLException {
        dbManager.setLectureVideoUrl(Integer.parseInt(lectureId), videoURL);
        resp.sendRedirect("/lecture");
    }

    @PostMapping("/edit-text")
    public void editLectureText(HttpServletResponse resp,
                                @RequestParam String lectureId,
                                @RequestParam String newText) throws SQLException, IOException {
        Lecture lec = dbManager.getLectureById(Integer.parseInt(lectureId));
        FileManager.writeToFile(Lecture.LECTURES_FILES_PATH, lec.getFileName(), newText);
        resp.sendRedirect("/lecture");
    }

    @GetMapping("/edit-quiz")
    public ModelAndView editQuiz(@RequestParam String lectureId) {
        ModelAndView ret = new ModelAndView("add-question");
        ret.addObject("lectureId", lectureId);
        return ret;
    }

}
