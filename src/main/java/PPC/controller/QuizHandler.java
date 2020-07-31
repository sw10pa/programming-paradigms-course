package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.Question;
import PPC.model.Record;
import PPC.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuizHandler {

    private final PPCDatabaseManager dbManager;

    public QuizHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @GetMapping("/quiz")
    public ModelAndView get(HttpServletResponse resp, HttpServletRequest req,
                            HttpSession ses, @RequestParam String lectureId) throws IOException, SQLException {
        if (ses.getAttribute("user") == null) resp.sendRedirect("/logout");

        ArrayList<Question> questions = dbManager.getQuestionsByLectureId(Integer.parseInt(lectureId));
        initializeQuiz(ses, questions);
        Question question = questions.get(0);
        ModelAndView ret = new ModelAndView("question-" + question.getQuestionType());

        ret.addObject("question", question);

        return ret;
    }

    private void initializeQuiz(HttpSession ses, ArrayList<Question> questions) {
        ses.setAttribute("currQuestionIndex", 0);
        ses.setAttribute("questions", questions);

        List<String> correctAnswers = new ArrayList<>();
        List<String> userAnswers = new ArrayList<>();

        for (Question question : questions) {
            String correctAnswer = question.getQuestionStructure().get(question.getRightAnswerIndex());
            correctAnswers.add(correctAnswer);
        }

        ses.setAttribute("correctAnswers", correctAnswers);
        ses.setAttribute("userAnswers", userAnswers);
        ses.setAttribute("correctAnswerCount", 0);
    }

    @PostMapping("/quiz")
    public ModelAndView post(HttpSession ses, HttpServletResponse resp, @RequestParam String answer) throws IOException, SQLException {
        if (ses.getAttribute("user") == null) resp.sendRedirect("/logout");

        ArrayList<Question> questions = (ArrayList<Question>) ses.getAttribute("questions");
        int index = (int) ses.getAttribute("currQuestionIndex");

        List<String> userAnswers = (List<String>) ses.getAttribute("userAnswers");
        userAnswers.add(answer);
        checkAnswer(ses, index, questions, answer);

        //move on to next question
        index++;
        if (index == questions.size()) return endQuiz(ses);
        Question newQuestion = questions.get(index);
        ModelAndView ret = new ModelAndView("question-" + newQuestion.getQuestionType());
        ret.addObject("question", newQuestion);
        ses.setAttribute("currQuestionIndex", index);
        return ret;
    }

    private void checkAnswer(HttpSession ses, int index, ArrayList<Question> questions, String answer) throws SQLException {
        Question question = questions.get(index);
        User user = (User) ses.getAttribute("user");
        String correctAnswer = question.getQuestionStructure().get(question.getRightAnswerIndex());
        if (correctAnswer.equals(answer)) {
            ses.setAttribute("correctAnswerCount", (int) ses.getAttribute("correctAnswerCount") + 1);
            dbManager.addRecord(new Record(user.getUserId(), question.getQuestionId()));
        }
    }


    private ModelAndView endQuiz(HttpSession ses) {
        ModelAndView ret = new ModelAndView("quiz-result");

        List<String> userAnswers = (List<String>) ses.getAttribute("userAnswers");
        ret.addObject("userAnswers", userAnswers);
        ses.removeAttribute("userAnswers");

        List<String> correctAnswers = (List<String>) ses.getAttribute("correctAnswers");
        ret.addObject("correctAnswers", correctAnswers);
        ses.removeAttribute("userAnswers");

        int correctAnswerCount = (int) ses.getAttribute("correctAnswerCount");
        ret.addObject("correctAnswerCount", correctAnswerCount);
        ses.removeAttribute("correctAnswerCount");

        ses.removeAttribute("currQuestionIndex");
        return ret;
    }

}
