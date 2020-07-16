package PPC.database;

import java.io.*;
import java.sql.*;
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
        FileManager.createFile(Lecture.LECTURE_FILES_PATH, lecture.getFileName());
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO lectures (lecture_name, file_name) VALUES (?, ?);");
        preparedStatement.setString(1, lecture.getLectureName());
        preparedStatement.setString(2, lecture.getFileName());
        preparedStatement.execute();
    }

    public void removeLectureByName(String lectureName) throws SQLException {
        FileManager.deleteFile(Lecture.LECTURE_FILES_PATH, lectureName + ".txt");
        PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM lectures WHERE lecture_name = ?;");
        preparedStatement.setString(1, lectureName);
        preparedStatement.execute();
    }

    public Lecture getLectureByName(String lectureName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM lectures WHERE lecture_name = ?;");
        preparedStatement.setString(1, lectureName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        return buildLecture(resultSet);
    }

    private Lecture buildLecture(ResultSet resultSet) throws SQLException {
        return new Lecture(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3));
    }

}
