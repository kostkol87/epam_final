package DAObjects;

import Utils.ConnectionPool.ConnectionPool;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EntitiesUtils {
    List<Direction> directions;
    List<Order> orders;
    List<User> users;

    public User getUser(int id){
        User user = new User();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT email, password, surname, name, patronomic, role FROM flight_discounter.user WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("result set is ready!");
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
            throw new RuntimeException(e);
        }finally {
            pool.free(connection);
        }
        return user;
    }

    public User getUser(String email) {
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
        }finally {
            pool.free(connection);
        }
        return user;
    }

    public boolean addUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM user WHERE email=?");
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id += resultSet.getInt("id");
                if (id > 0) {
                    System.out.println("mail arleady exist");
                    preparedStatement.close();
                    pool.free(connection);
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO flight_discounter.user " +
                            "(email, password, surname, name, patronomic, role)" +
                            " VALUES (?,?,?,?,?,1)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getPatronomic());
            System.out.println(user);
            preparedStatement.executeUpdate();
            System.out.println("user added!");
            preparedStatement.close();
            pool.free(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.free(connection);
        }
        return true;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static int directionsCount;

    public Direction getDirection(int id) {
        Direction direction = new Direction();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT departure, dep_date, destination, dest_date," +
                    " basic_price, date_multiplier, fill_multiplier, " +
                    "capacity, left_places FROM directions WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                direction.setId(id);
                direction.setDeparture(resultSet.getString("departure"));
                direction.setDepTime(sdf.parse(resultSet.getString("dep_date")));
                direction.setDestination(resultSet.getString("destination"));
                direction.setDestTime(sdf.parse(resultSet.getString("dest_date")));
                direction.setDateMultiplier(resultSet.getDouble("date_multiplier"));
                direction.setFillMultiplier(resultSet.getDouble("fill_multiplier"));
                direction.setCapacity(resultSet.getInt("capacity"));
                direction.setLeftPlaces(resultSet.getInt("left_places"));
                direction.setBasicPrice(resultSet.getDouble("basic_price"));
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }finally {
            pool.free(connection);
        }

        return direction;
    }

    public List<Direction> getDirections() {
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
            while (resultSet.next()) {
                if(resultSet.getInt("left_places")<1){
                    continue;
                }
                newDirection = new Direction();
                newDirection.setId(resultSet.getInt("id"));
                newDirection.setDeparture(resultSet.getString("departure"));
                newDirection.setDepTime(sdf.parse(resultSet.getString("dep_date")));
                newDirection.setDestination(resultSet.getString("destination"));
                newDirection.setDestTime(sdf.parse(resultSet.getString("dest_date")));
                newDirection.setDateMultiplier(resultSet.getDouble("date_multiplier"));
                newDirection.setFillMultiplier(resultSet.getDouble("fill_multiplier"));
                newDirection.setCapacity(resultSet.getInt("capacity"));
                newDirection.setLeftPlaces(resultSet.getInt("left_places"));
                newDirection.setBasicPrice(resultSet.getDouble("basic_price"));
                directions.add(newDirection);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }finally {
            pool.free(connection);
        }
        directionsCount = directions.size();

        return directions;
    }
    public Order addOrder(Direction direction, User user,int quantity, boolean baggage, boolean priority, double summa){
        if (quantity>direction.getLeftPlaces()){
            return null;
        }
        Order newOrder = new Order();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO orders (direction, quantity, baggage, priority_queue, client, summa, paid)" +
                            " VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, direction.getId());
            preparedStatement.setInt(2, quantity);
            preparedStatement.setBoolean(3, baggage);
            preparedStatement.setBoolean(4, priority);
            preparedStatement.setInt(5, user.getId());
            preparedStatement.setDouble(6, summa);
            preparedStatement.setBoolean(7, false);

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                newOrder.setId(newId);
            }
            newOrder.setDirection(direction);
            newOrder.setQuantity(quantity);
            newOrder.setBaggage(baggage);
            newOrder.setPriorityQueue(priority);
            newOrder.setClient(user);
            newOrder.setPaid(false);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.free(connection);
        }
        return newOrder;
    }
    public List<Order> getOrders(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, direction, quantity, baggage, priority_queue, client, summa, paid FROM orders WHERE client=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order;
            while (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setDirection(getDirection(resultSet.getInt("direction")));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setBaggage(resultSet.getBoolean("baggage"));
                order.setPriorityQueue(resultSet.getBoolean("priority_queue"));
                order.setClient(getUser(resultSet.getInt("client")));
                order.setSumma(resultSet.getDouble("summa"));
                order.setPaid(resultSet.getBoolean("paid"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.free(connection);
        }
        return orders;
    }
public Order getOrder(int id){
    Order order = new Order();
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    try {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, direction, quantity, baggage, priority_queue, client, summa, paid FROM orders WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            order.setId(resultSet.getInt("id"));
            order.setDirection(getDirection(resultSet.getInt("direction")));
            order.setQuantity(resultSet.getInt("quantity"));
            order.setBaggage(resultSet.getBoolean("baggage"));
            order.setPriorityQueue(resultSet.getBoolean("priority_queue"));
            order.setClient(getUser(resultSet.getInt("client")));
            order.setSumma(resultSet.getDouble("summa"));
            order.setPaid(resultSet.getBoolean("paid"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return order;
}
    public void updateOrderPay(int orderId, boolean isPaid){
        EntitiesUtils entitiesUtils = new EntitiesUtils();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT left_places FROM directions WHERE id=(SELECT direction FROM  orders WHERE id=?)");
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int left_places=0;
            while (resultSet.next()){
               left_places = resultSet.getInt("left_places");
            }
            preparedStatement.close();
            Order order = entitiesUtils.getOrder(orderId);
            if (order!=null) {
                if (left_places >= order.getQuantity()) {
                    preparedStatement = connection.prepareStatement("UPDATE orders SET paid=? WHERE id = ?");
                    preparedStatement.setBoolean(1, isPaid);
                    preparedStatement.setInt(2, orderId);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    if (isPaid){
                        preparedStatement = connection.prepareStatement("UPDATE directions SET left_places=left_places-? WHERE id = (SELECT direction FROM  orders WHERE id=?)");
                        preparedStatement.setInt(1,order.getQuantity());
                        preparedStatement.setInt(2, orderId);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.free(connection);
        }
    }
}