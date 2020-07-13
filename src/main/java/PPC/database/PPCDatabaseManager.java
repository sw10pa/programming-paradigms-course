package PPC.database;

import java.sql.*;
import PPC.model.*;

public class PPCDatabaseManager {

    private final Connection connection;

    public PPCDatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO students (first_name, last_name, email, password) VALUES (?, ?, ?, ?);");
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setString(3, student.getEmail());
        preparedStatement.setString(4, student.getPassword());
        preparedStatement.execute();
    }

    public void removeStudentByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM students WHERE email = ?;");
        preparedStatement.setString(1, email);
        preparedStatement.execute();
    }

    public Student getStudentByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM students WHERE email = ?;");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        return buildStudent(resultSet);
    }

    private Student buildStudent(ResultSet resultSet) throws SQLException {
        return new Student(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5));
    }

    public void addLecturer(Lecturer lecturer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO lecturers (first_name, last_name, email, password) VALUES (?, ?, ?, ?);");
        preparedStatement.setString(1, lecturer.getFirstName());
        preparedStatement.setString(2, lecturer.getLastName());
        preparedStatement.setString(3, lecturer.getEmail());
        preparedStatement.setString(4, lecturer.getPassword());
        preparedStatement.execute();
    }

    public void removeLecturerByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM lecturers WHERE email = ?;");
        preparedStatement.setString(1, email);
        preparedStatement.execute();
    }

    public Lecturer getLecturerByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM lecturers WHERE email = ?;");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        return buildLecturer(resultSet);
    }

    private Lecturer buildLecturer(ResultSet resultSet) throws SQLException {
        return new Lecturer(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5));
    }

}
