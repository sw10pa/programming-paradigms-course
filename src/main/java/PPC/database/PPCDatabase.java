package PPC.database;

import java.sql.*;

public class PPCDatabase {

    private static final String USER = "root";
    private static final String PASS = "Stephane27";

    private Connection connection;

    public PPCDatabase() throws SQLException, ClassNotFoundException {
        openConnection();
        createDatabase();
        createUsersTable();
        createLecturesTable();
        createQuestionsTable();
        createRecordsTable();
    }

    public Connection getConnection() {
        return connection;
    }

    private void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", USER, PASS);
    }

    private void createDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS ppc;");
        statement.execute("USE ppc;");
    }

    private void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS users" +
                "(user_id INT(8) PRIMARY KEY AUTO_INCREMENT," +
                " first_name VARCHAR(64) NOT NULL," +
                " last_name VARCHAR(64) NOT NULL," +
                " email VARCHAR(64) NOT NULL UNIQUE," +
                " password VARCHAR(256) NOT NULL," +
                " status VARCHAR(64) CHECK (status IN ('ADM', 'LEC', 'STU')));");
    }

    private void createLecturesTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS lectures" +
                "(lecture_id INT(8) PRIMARY KEY AUTO_INCREMENT," +
                " lecture_name VARCHAR(64) NOT NULL UNIQUE," +
                " file_name VARCHAR(64) NOT NULL UNIQUE," +
                " video_url VARCHAR(64) DEFAULT NULL);");
    }

    private void createQuestionsTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS questions" +
                "(question_id INT(8) PRIMARY KEY AUTO_INCREMENT," +
                " lecture_id INT(8) REFERENCES lectures(lecture_id)," +
                " file_name VARCHAR(64) NOT NULL UNIQUE," +
                " question_type VARCHAR(64) CHECK (question_type IN ('TF', 'MC', 'QR')));");
    }

    private void createRecordsTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS records" +
                "(record_id INT(8) PRIMARY KEY AUTO_INCREMENT," +
                " user_id INT(8) REFERENCES users(user_id)," +
                " question_id INT(8) REFERENCES questions(question_id));");
    }

}
