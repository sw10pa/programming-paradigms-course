package PPC.test;

import java.io.*;
import java.sql.*;
import java.util.*;

import PPC.model.*;
import PPC.database.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionsTableTest {

    private PPCDatabaseManager dbManager;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }

    @Test
    public void addQuestionsTest() throws IOException, SQLException {
        ArrayList<String> questionStructure = new ArrayList<>();

        questionStructure.add("TF Question?");
        questionStructure.add("True");
        questionStructure.add("False");
        dbManager.addQuestion(new Question(1, Question.TRUE_FALSE, 2, questionStructure));
        questionStructure.clear();

        questionStructure.add("MC Question?");
        questionStructure.add("Choice 1");
        questionStructure.add("Choice 2");
        questionStructure.add("Choice 3");
        questionStructure.add("Choice 4");
        dbManager.addQuestion(new Question(1, Question.MULTIPLE_CHOICE, 3, questionStructure));
        questionStructure.clear();

        questionStructure.add("QR Question?");
        questionStructure.add("Answer");
        dbManager.addQuestion(new Question(1, Question.QUESTION_RESPONSE, 1, questionStructure));
        questionStructure.clear();

        ArrayList<Question> questions = dbManager.getQuestionsByLectureId(1);
        assertEquals(3, questions.size());

        assertEquals(1, questions.get(0).getQuestionId());
        assertEquals(1, questions.get(0).getLectureId());
        assertEquals("1.txt", questions.get(0).getFileName());
        assertEquals(Question.TRUE_FALSE, questions.get(0).getQuestionType());
        assertEquals(2, questions.get(0).getRightAnswerIndex());
        assertEquals(3, questions.get(0).getQuestionStructure().size());
        assertEquals("TF Question?", questions.get(0).getQuestionStructure().get(0));
        assertEquals("True", questions.get(0).getQuestionStructure().get(1));
        assertEquals("False", questions.get(0).getQuestionStructure().get(2));

        assertEquals(2, questions.get(1).getQuestionId());
        assertEquals(1, questions.get(1).getLectureId());
        assertEquals("2.txt", questions.get(1).getFileName());
        assertEquals(Question.MULTIPLE_CHOICE, questions.get(1).getQuestionType());
        assertEquals(3, questions.get(1).getRightAnswerIndex());
        assertEquals(5, questions.get(1).getQuestionStructure().size());
        assertEquals("MC Question?", questions.get(1).getQuestionStructure().get(0));
        assertEquals("Choice 1", questions.get(1).getQuestionStructure().get(1));
        assertEquals("Choice 2", questions.get(1).getQuestionStructure().get(2));
        assertEquals("Choice 3", questions.get(1).getQuestionStructure().get(3));
        assertEquals("Choice 4", questions.get(1).getQuestionStructure().get(4));

        assertEquals(3, questions.get(2).getQuestionId());
        assertEquals(1, questions.get(2).getLectureId());
        assertEquals("3.txt", questions.get(2).getFileName());
        assertEquals(Question.QUESTION_RESPONSE, questions.get(2).getQuestionType());
        assertEquals(1, questions.get(2).getRightAnswerIndex());
        assertEquals(2, questions.get(2).getQuestionStructure().size());
        assertEquals("QR Question?", questions.get(2).getQuestionStructure().get(0));
        assertEquals("Answer", questions.get(2).getQuestionStructure().get(1));
    }

}
