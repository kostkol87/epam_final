package Utils;


import DAObjects.Direction;
import Utils.ConnectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DirectionsGenerator {
    private static List<String> cities;
    Random rand = new Random();
    private void getCities() throws SQLException {
        cities = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT city FROM cities;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            cities.add(resultSet.getString("city"));
        }
        preparedStatement.close();
        ConnectionPool.getInstance().free(connection);
    }
    private Date[] getRandomDate(){
        Random rnd = new Random();
        long ms = new Date().getTime() + (Math.abs(rnd.nextLong()) % (1L * 365 * 24 * 60 * 60 * 1000));
        Date dt[] = {new Date(ms), new Date(ms + ThreadLocalRandom.current().nextLong(7200000L, 21600000L))};

        return dt;
    }
    private Direction generateDirection() throws SQLException {
        getCities();
        Date[] randomDate = getRandomDate();
        Direction direction = new Direction();
        direction.setDeparture(cities.get(ThreadLocalRandom.current().nextInt(cities.size())));
        direction.setDepTime(randomDate[0]);
        direction.setDestination(cities.get(rand.nextInt(cities.size())));
        direction.setDestTime(randomDate[1]);
        direction.setBasicPrice(ThreadLocalRandom.current().nextDouble(40.0, 150.0));
        direction.setDateMultiplier(System.currentTimeMillis() % 2 == 0 ? 1 : 1.2);
        direction.setFillMultiplier(System.currentTimeMillis() % 3 == 0 ? 1 : 1.2);
        direction.setCapacity(ThreadLocalRandom.current().nextInt(50,200));
        return direction;
    }

    private void directionToDb() throws SQLException {
        List<Direction> directions = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO directions (departure, dep_date, destination, dest_date, basic_price, date_multiplier, fill_multiplier, capacity) VALUES (?,?,?,?,?,?,?,?);");

        for (int i = 0; i < 97; i++) {
            directions.add(new DirectionsGenerator().generateDirection());
        }
        for (Direction direction:directions){
            preparedStatement.setString(1,direction.getDeparture());
            preparedStatement.setDate(2, new java.sql.Date(direction.getDepTime().getTime()));
            preparedStatement.setString(3, direction.getDestination());
            preparedStatement.setDate(4, new java.sql.Date(direction.getDestTime().getTime()));
            preparedStatement.setDouble(5, direction.getBasicPrice());
            preparedStatement.setDouble(6, direction.getDateMultiplier());
            preparedStatement.setDouble(7, direction.getFillMultiplier());
            preparedStatement.setInt(8, direction.getCapacity());
            preparedStatement.executeUpdate();
        }
        System.out.println("ALL DONE");
        preparedStatement.close();
        ConnectionPool.getInstance().free(connection);
    }
    public static void main(String[] args) throws SQLException {
//        System.out.println(new LocalDateTime());
        new DirectionsGenerator().directionToDb();
    }
}
