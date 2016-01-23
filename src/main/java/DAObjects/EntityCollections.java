package DAObjects;

import Utils.ConnectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            System.out.println("result set is ready!");
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
                pool.free(connection);
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public boolean addUser(User user){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM user WHERE email=?");
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            int id=0;
            while (resultSet.next()){
                id += resultSet.getInt("id");
                if (id > 0){
                    System.out.println("mail arleady exist");
                    preparedStatement.close();
                    pool.free(connection);
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO flight_discounter.user " +
                            "(email, password, surname, name, patronomic, role)" +
                            " VALUES (?,?,?,?,?,1)");
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getSurname());
            preparedStatement.setString(4,user.getName());
            preparedStatement.setString(5,user.getPatronomic());
            System.out.println(user);
            preparedStatement.executeUpdate();
            System.out.println("user added!");
            preparedStatement.close();
            pool.free(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
public static int directionsCount;
    public List<Direction> getDirections(){
        List<Direction> directions = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT id, departure, dep_date, destination, dest_date," +
                            " basic_price, date_multiplier, fill_multiplier, " +
                            "capacity, left_places FROM directions"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            Direction newDirection;
            while (resultSet.next()){
                newDirection = new Direction();
                newDirection.setId(resultSet.getInt("id"));
                newDirection.setDeparture(resultSet.getString("departure"));
                newDirection.setDepTime(sdf.parse(resultSet.getString("dep_date")));
                newDirection.setDestination(resultSet.getString("destination"));
                newDirection.setDestTime(sdf.parse(resultSet.getString("dest_date")));
                newDirection.setBasicPrice(resultSet.getDouble("basic_price"));
                newDirection.setDateMultiplier(resultSet.getDouble("date_multiplier"));
                newDirection.setFillMultiplier(resultSet.getDouble("fill_multiplier"));
                newDirection.setCapacity(resultSet.getInt("capacity"));
                newDirection.setLeftPlaces(resultSet.getInt("left_places"));
                directions.add(newDirection);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        directionsCount = directions.size();
        pool.free(connection);
        return directions;
    }
}
