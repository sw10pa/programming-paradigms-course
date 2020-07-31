package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class QuizHandler {

    private final PPCDatabaseManager dbManager;

    public QuizHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping("/quiz")
    public ModelAndView get(HttpServletRequest req, HttpSession ses, @RequestParam String lectureId) throws FileNotFoundException, SQLException {

        int index = 0;
        ArrayList<Question> questions = dbManager.getQuestionsByLectureId(Integer.parseInt(lectureId));
        Question question = questions.get(index);
        ModelAndView ret = new ModelAndView("question-" + question.getQuestionType());

        ret.addObject("question", question);
        ses.setAttribute("questionCount", index + 1);
        ses.setAttribute("questions", questions);
        return ret;
    }

    @PostMapping("/quiz")
    public ModelAndView post(HttpSession ses) {

        //move on to next question
        int index = (int) ses.getAttribute("questionCount");
        ArrayList<Question> questions = (ArrayList<Question>) ses.getAttribute("questions");

        Question question = questions.get(index);
        ModelAndView ret = new ModelAndView("question-" + question.getQuestionType());

        ret.addObject("question", question);
        ses.setAttribute("questionCount", index + 1);
        return ret;
    }

}
