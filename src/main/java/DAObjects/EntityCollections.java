package DAObjects;

import Utils.ConnectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EntityCollections {
    List<Direction> directions;
    List<Order> orders;
    List<User> users;

    public User getUser(String email){
        User user = new User();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, password, surname, name, patronomic, role FROM flight_discounter.user WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                resultSet.next();
                user.setId(resultSet.getInt("id"));
                user.setEmail(email);
                user.setPassword(resultSet.getString("password"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPatronomic(resultSet.getString("patronomic"));
                user.setRole(resultSet.getInt("role"));
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
