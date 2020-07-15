package PPC.database;

import java.sql.*;

public class PPCDatabase {

    private static final String USER = "root";
    private static final String PASS = "dato1312";

    private Connection connection;

    public PPCDatabase() throws SQLException, ClassNotFoundException {
        openConnection();
        createDatabase();
        createUsersTable();
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
                "(user_id INT(6) PRIMARY KEY AUTO_INCREMENT," +
                " first_name VARCHAR(30) NOT NULL," +
                " last_name VARCHAR(30) NOT NULL," +
                " email VARCHAR(30) NOT NULL UNIQUE," +
                " password VARCHAR(30) NOT NULL," +
                " status VARCHAR(3) CHECK (status IN ('ADM', 'LEC', 'STU')));");
    }

}
