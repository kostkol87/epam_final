package DAO.Utils;

import DAO.Entities.User;
import Utils.cp.Pool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private static final Logger log = Logger.getLogger(Users.class);
    public static User getUser(int id) {
        User user = new User();
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT email, password, surname, name, patronomic, role FROM flight_discounter.user WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    user.setId(id);
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setName(resultSet.getString("name"));
                    user.setPatronomic(resultSet.getString("patronomic"));
                    user.setRole(resultSet.getInt("role"));
                }
                preparedStatement.close();
            }
        } catch (SQLException e) {
            log.warn("SQLException in getUser(id)");
            throw new RuntimeException(e);
        }
        return user;
    }

    public static User getUser(String email) {
        User user = new User();
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, password, surname, name, patronomic, role FROM flight_discounter.user WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(email);
                    user.setPassword(resultSet.getString("password"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setName(resultSet.getString("name"));
                    user.setPatronomic(resultSet.getString("patronomic"));
                    user.setRole(resultSet.getInt("role"));
                }
                preparedStatement.close();
                return user;
            }

        } catch (SQLException e) {
            log.warn("SQLException in getUser(eMail)");
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean addUser(User user) {
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM user WHERE email=?");
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id += resultSet.getInt("id");
                if (id > 0) {
                    log.debug("mail arleady exist in addUser");
                    preparedStatement.close();
                    return false;
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException in addOrder");
            throw new RuntimeException(e);
        }
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO flight_discounter.user " +
                            "(email, password, surname, name, patronomic, role)" +
                            " VALUES (?,?,?,?,?,1)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getPatronomic());
            preparedStatement.executeUpdate();
            log.debug("SQLException in addOrder");
            preparedStatement.close();

        } catch (SQLException e) {
            log.warn("SQLException in addOrder");
            e.printStackTrace();
        }
        return true;
    }
}
