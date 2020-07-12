package PPC.database;

import java.sql.*;

public class PPCDatabase {

    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String PASS = "Stephane27";

    private Connection connection;

    public PPCDatabase() throws SQLException, ClassNotFoundException {
        openConnection();
        createDatabase();

        createStudentsTable();
        createLecturersTable();
    }

    private void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT, USER, PASS);
    }

    private void createDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS ppc;");
        statement.execute("USE ppc;");
    }

    private void createStudentsTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS students" +
                "(student_id INT(6) PRIMARY KEY AUTO_INCREMENT," +
                " first_name VARCHAR(25) NOT NULL," +
                " last_name VARCHAR(25) NOT NULL," +
                " email VARCHAR(25) NOT NULL UNIQUE," +
                " password VARCHAR(25) NOT NULL);");
    }

    private void createLecturersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS lecturers" +
                "(lecturer_id INT(6) PRIMARY KEY AUTO_INCREMENT," +
                " first_name VARCHAR(25) NOT NULL," +
                " last_name VARCHAR(25) NOT NULL," +
                " email VARCHAR(25) NOT NULL UNIQUE," +
                " password VARCHAR(25) NOT NULL);");
    }

}
