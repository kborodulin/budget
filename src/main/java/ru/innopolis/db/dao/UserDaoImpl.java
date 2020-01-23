package ru.innopolis.db.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.db.model.User;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        try (Connection connection = ConnectionDB.connectDB()) {
            int id = 0;
            String sql = "select max(userid) userid from users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("userid");
            }
            id++;

            String sqlUser = "insert into users (userid, login, password, email) values (?, ?, ?, ?)";
            PreparedStatement preparedStatementUser = connection.prepareStatement(sqlUser);
            preparedStatementUser.setInt(1, id);
            preparedStatementUser.setString(2, user.getUsername());
            preparedStatementUser.setString(3, user.getPassword());
            preparedStatementUser.setString(4, user.getEmail());
            preparedStatementUser.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
