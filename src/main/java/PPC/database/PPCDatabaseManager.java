package PPC.database;

import java.io.*;
import java.sql.*;
import java.util.*;
import PPC.model.*;
import PPC.filesystem.*;

public class PPCDatabaseManager {

    private final Connection connection;

    public PPCDatabaseManager(Connection connection) {
        this.connection = connection;
    }

    /**************************************** USERS TABLE ****************************************/

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO users (first_name, last_name, email, password, status) VALUES (?, ?, ?, ?, ?);");
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, User.STUDENT);
        preparedStatement.execute();
    }

    public void removeUserByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM users WHERE email = ?;");
        preparedStatement.setString(1, email);
        preparedStatement.execute();
    }

    public User getUserByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM users WHERE email = ?;");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        return buildUser(resultSet);
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM users;");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(buildUser(resultSet));
        }
        return users;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6));
    }

    public void setUserStatus(String email, String status) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE users SET status = ? WHERE email = ?;");
        preparedStatement.setString(1, status);
        preparedStatement.setString(2, email);
        preparedStatement.execute();
    }

    /**************************************** LECTURES TABLE ****************************************/

    public void addLecture(Lecture lecture) throws SQLException, IOException {
        FileManager.createFile(Lecture.LECTURES_FILES_PATH, lecture.getFileName());
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO lectures (lecture_name, file_name) VALUES (?, ?);");
        preparedStatement.setString(1, lecture.getLectureName());
        preparedStatement.setString(2, lecture.getFileName());
        preparedStatement.execute();
    }

    public void removeLectureByName(String lectureName) throws SQLException {
        FileManager.deleteFile(Lecture.LECTURES_FILES_PATH, lectureName + ".txt");
        PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM lectures WHERE lecture_name = ?;");
        preparedStatement.setString(1, lectureName);
        preparedStatement.execute();
    }

    public Lecture getLectureById(int lectureId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM lectures WHERE lecture_id = ?;");
        preparedStatement.setInt(1, lectureId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        return buildLecture(resultSet);
    }

    public Lecture getLectureByName(String lectureName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM lectures WHERE lecture_name = ?;");
        preparedStatement.setString(1, lectureName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        return buildLecture(resultSet);
    }

    public ArrayList<Lecture> getAllLectures() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM lectures;");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Lecture> lectures = new ArrayList<>();
        while (resultSet.next()) {
            lectures.add(buildLecture(resultSet));
        }
        return lectures;
    }

    private Lecture buildLecture(ResultSet resultSet) throws SQLException {
        return new Lecture(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4));
    }

    public void setLectureVideoUrl(int lectureId, String videoUrl) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE lectures SET video_url = ? WHERE lecture_id = ?;");
        preparedStatement.setString(1, videoUrl);
        preparedStatement.setInt(2, lectureId);
        preparedStatement.execute();
    }

    /**************************************** QUESTIONS TABLE ****************************************/

    public void addQuestion(Question question) throws SQLException, IOException {
        String fileName = getQuestionsCount() + 1 + ".txt";
        FileManager.createFile(Question.QUESTIONS_FILES_PATH, fileName);
        FileManager.writeQuestionToFile(Question.QUESTIONS_FILES_PATH, fileName, question.getRightAnswerIndex(), question.getQuestionStructure());
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO questions (lecture_id, file_name, question_type) VALUES (?, ?, ?);");
        preparedStatement.setInt(1, question.getLectureId());
        preparedStatement.setString(2, fileName);
        preparedStatement.setString(3, question.getQuestionType());
        preparedStatement.execute();
    }

    private int getQuestionsCount() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT COUNT(*) FROM questions;");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public ArrayList<Question> getQuestionsByLectureId(int lectureId) throws SQLException, FileNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM questions WHERE lecture_id = ?;");
        preparedStatement.setInt(1, lectureId);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Question> questions = new ArrayList<>();
        while (resultSet.next()) {
            questions.add(buildQuestion(resultSet));
        }
        return questions;
    }

    private Question buildQuestion(ResultSet resultSet) throws SQLException, FileNotFoundException {
        int questionId = resultSet.getInt(1);
        int lectureId = resultSet.getInt(2);
        String fileName = resultSet.getString(3);
        String questionType = resultSet.getString(4);
        int rightAnswerIndex = FileManager.readRightAnswerIndex(Question.QUESTIONS_FILES_PATH, fileName);
        ArrayList<String> questionStructure = FileManager.readQuestionStructure(Question.QUESTIONS_FILES_PATH, fileName);
        return new Question(questionId, lectureId, fileName, questionType, rightAnswerIndex, questionStructure);
    }

    /**************************************** RECORDS TABLE ****************************************/

    public int getUserScore(String email) {
        return 0;
    }

}
