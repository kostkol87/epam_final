package dataBase.DAO;

import dataBase.entities.Direction;
import dataBase.connectionPool.Pool;
import org.apache.log4j.Logger;
import service.logicServices.DirectionsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Directions {
    private static final Logger log = Logger.getLogger(Directions.class);

    static List<Direction> directions;

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public int directionsCount;

    public void addDirection(String departure, Date depTime, String destination, Date destTime,
                                    double basicPrice, double dateMultiplier, double fillMultiplier, int capacity){

        try(Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO directions " +
                            "(departure, dep_date, destination, dest_date, basic_price, date_multiplier, fill_multiplier, capacity, left_places) " +
                            "VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, departure);
            preparedStatement.setDate(2, new java.sql.Date(depTime.getTime()));
            preparedStatement.setString(3, destination);
            preparedStatement.setDate(4, new java.sql.Date(destTime.getTime()));
            preparedStatement.setDouble(5, basicPrice);
            preparedStatement.setDouble(6, dateMultiplier);
            preparedStatement.setDouble(7, fillMultiplier);
            preparedStatement.setInt(8, capacity);
            preparedStatement.setInt(9, capacity);
            preparedStatement.executeUpdate();
            log.debug("new direction was added !!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Direction getDirection(int id) {
        Direction result = null;
        if (directions == null){
            getDirections();
        }

        for (Direction direction : directions)
            if (direction.getId() == id) {
                result = direction;
            }
        return result;
    }

    public List<Direction> getDirections() {
        directions = new ArrayList<>();
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                            "SELECT id, departure, dep_date, destination, dest_date," +
                            " basic_price, date_multiplier, fill_multiplier, " +
                            "capacity, left_places FROM directions"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            Direction newDirection;
            while (resultSet.next()) {
                newDirection = new Direction();
                newDirection.setId(resultSet.getInt("id"));
                newDirection.setDeparture(resultSet.getString("departure"));
                newDirection.setDepTime(SDF.parse(resultSet.getString("dep_date")));
                newDirection.setDestination(resultSet.getString("destination"));
                newDirection.setDestTime(SDF.parse(resultSet.getString("dest_date")));
                newDirection.setDateMultiplier(resultSet.getDouble("date_multiplier"));
                newDirection.setFillMultiplier(resultSet.getDouble("fill_multiplier"));
                newDirection.setCapacity(resultSet.getInt("capacity"));
                newDirection.setLeftPlaces(resultSet.getInt("left_places"));
                newDirection.setBasicPrice(resultSet.getDouble("basic_price"));
                newDirection.setBasicPrice(DirectionsService.getActualPrice(newDirection));

                directions.add(newDirection);
            }
        } catch (SQLException e) {
            log.warn("SQLException in addOrder");
            e.printStackTrace();
        } catch (ParseException e) {
            log.warn("ParseException in addOrder");
            e.printStackTrace();
        }
        directionsCount = directions.size();

        return directions;
    }

    public boolean isEmptyDirection(int id){
        boolean result = false;
        try(Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM orders WHERE direction=? AND paid=1");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = !resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void removeDirection(int id){
        try(Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE directions, orders  FROM directions  INNER JOIN orders\n" +
                            "WHERE directions.id = orders.direction and directions.id=?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.debug("direction with id=" + id + "was deleted!");
    }
}
