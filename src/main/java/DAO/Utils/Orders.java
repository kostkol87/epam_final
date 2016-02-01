package DAO.Utils;

import DAO.Entities.Direction;
import DAO.Entities.Order;
import DAO.Entities.User;
import Utils.cp.Pool;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    private static final Logger log = Logger.getLogger(Orders.class);
    static List<Order> orders;


    public static Order addOrder(Direction direction, User user, int quantity, boolean baggage, boolean priority, double summa) {
        if (quantity > direction.getLeftPlaces()) {
            return null;
        }
        Order newOrder = new Order();
        PreparedStatement preparedStatement;
        try (Connection connection = Pool.getInstance().getConnection()){
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
            log.warn("SQLException in addOrder");
            e.printStackTrace();
        }
        return newOrder;
    }

    public static List<Order> getOrders(int id) {
        User user = null;
        orders = new ArrayList<>();
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, direction, quantity, baggage, priority_queue, client, summa, paid FROM orders WHERE client=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order;
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setDirection(Directions.getDirection(resultSet.getInt("direction")));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setBaggage(resultSet.getBoolean("baggage"));
                order.setPriorityQueue(resultSet.getBoolean("priority_queue"));
                if (user == null) {
                    user = Users.getUser(resultSet.getInt("client"));
                    order.setClient(user);
                } else {
                    order.setClient(user);
                }
                order.setSumma(resultSet.getDouble("summa"));
                order.setPaid(resultSet.getBoolean("paid"));
                orders.add(order);
            }
        } catch (SQLException e) {
            log.warn("SQLException in getOrders");
            e.printStackTrace();
        }
        user = null;
        return orders;
    }

    public static Order getOrder(int id) {
        Order order = new Order();
        User user = null;
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, direction, quantity, baggage, priority_queue, client, summa, paid FROM orders WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setDirection(Directions.getDirection(resultSet.getInt("direction")));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setBaggage(resultSet.getBoolean("baggage"));
                order.setPriorityQueue(resultSet.getBoolean("priority_queue"));
//                order.setClient(getUser(resultSet.getInt("client")));
                if (user == null) {
                    user = Users.getUser(resultSet.getInt("client"));
                    order.setClient(user);
                } else {
                    order.setClient(user);
                }
                order.setSumma(resultSet.getDouble("summa"));
                order.setPaid(resultSet.getBoolean("paid"));
            }
        } catch (SQLException e) {
            log.warn("SQLException in getOrder(id)");
            e.printStackTrace();
        }
        user = null;
        return order;
    }

    public static void updateOrderPay(int orderId, boolean isPaid) {
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT left_places FROM directions WHERE id=(SELECT direction FROM  orders WHERE id=?)");
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int left_places = 0;
            while (resultSet.next()) {
                left_places = resultSet.getInt("left_places");
            }
            preparedStatement.close();
            Order order = getOrder(orderId);
            if (order != null) {
                if (left_places >= order.getQuantity()) {
                    preparedStatement = connection.prepareStatement("UPDATE orders SET paid=? WHERE id = ?");
                    preparedStatement.setBoolean(1, isPaid);
                    preparedStatement.setInt(2, orderId);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    if (isPaid) {
                        preparedStatement = connection.prepareStatement("UPDATE directions SET left_places=left_places-? WHERE id = (SELECT direction FROM  orders WHERE id=?)");
                        preparedStatement.setInt(1, order.getQuantity());
                        preparedStatement.setInt(2, orderId);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    }

                }
            }
        } catch (SQLException e) {
            log.warn("SQLException in updateOrderPay");
            e.printStackTrace();
        }
    }

    public static void removeOrder(int orderId) {
        try (Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM orders WHERE id=?"
            );
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn("SQLException in removeOrder");
            e.printStackTrace();
        }
    }

    public static void changeOrder(int orderId, int passangersCount, boolean needBaggage, boolean needPriority){
        try(Connection connection = Pool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT direction FROM orders WHERE id=?");
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int directionId = -1;
            while (resultSet.next()){
                directionId = resultSet.getInt("direction");
            }
            Direction direction = Directions.getDirection(directionId);
            double currentPrice = direction.getBasicPrice();
            if (needBaggage){
                currentPrice += 45.0;
            }
            if (needPriority){
                currentPrice += 30;
            }
            currentPrice = currentPrice*passangersCount;

            preparedStatement = connection.prepareStatement("UPDATE orders SET quantity=?, baggage=?, priority_queue=?, summa=? WHERE  id=?");
            preparedStatement.setInt(1, passangersCount);
            preparedStatement.setBoolean(2, needBaggage);
            preparedStatement.setBoolean(3, needPriority);
            preparedStatement.setDouble(4, currentPrice);
            preparedStatement.setInt(5, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn("SQLException in changeOrder");
            e.printStackTrace();
        }
    }
}
