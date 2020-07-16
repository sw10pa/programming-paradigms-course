package PPC.database;

import java.sql.*;
import PPC.model.*;

public class PPCDatabaseManager {

    private final Connection connection;

    public PPCDatabaseManager(Connection connection) {
        this.connection = connection;
    }

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

    public void removeUserByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM users WHERE email = ?;");
        preparedStatement.setString(1, email);
        preparedStatement.execute();
    }

}
