package PPC.model;

import java.util.*;

public class Question {

    public final static String QUESTIONS_FILES_PATH = "src/main/webapp/resources/question/";

    public static final String TRUE_FALSE = "TF";
    public static final String MULTIPLE_CHOICE = "MC";
    public static final String QUESTION_RESPONSE = "QR";

    int questionId;
    int lectureId;
    String fileName;
    String questionType;
    int rightAnswerIndex;
    ArrayList<String> questionStructure;

    public Question(int questionId, int lectureId, String fileName, String questionType, int rightAnswerIndex, ArrayList<String> questionStructure) {
        this.questionId = questionId;
        this.lectureId = lectureId;
        this.fileName = fileName;
        this.questionType = questionType;
        this.rightAnswerIndex = rightAnswerIndex;
        this.questionStructure = questionStructure;
    }

    public Question(int lectureId, String questionType, int rightAnswerIndex, ArrayList<String> questionStructure) {
        this.lectureId = lectureId;
        this.questionType = questionType;
        this.rightAnswerIndex = rightAnswerIndex;
        this.questionStructure = questionStructure;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getLectureId() {
        return lectureId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getQuestionType() {
        return questionType;
    }

    public int getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public ArrayList<String> getQuestionStructure() {
        return questionStructure;
    }

}
