package Utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {

    public static Connection getCon() {
        System.out.println("IN GET CONNECTION");
        Connection connection = null;
        try {
            connection = AppServerUtils.<DataSource>localJndiSearch("jdbc/DiscountFlights", DataSource.class).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
