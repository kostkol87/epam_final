package Utils.ConnectionPool;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionPool {

    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    public static final String PROPERTIES_PATH = "C:\\epam_final\\src\\main\\resources\\pool.properties";
    private static ConnectionPool INSTANCE;
    private HashMap<Connection, Boolean> thePool;


    public static ConnectionPool getInstance() {

        if (INSTANCE == null)
            synchronized (ConnectionPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                    log.debug("connection pool instance was created!");
                }
            }

        return INSTANCE;
    }

    final private String URL;
    final private String USER;
    final private String PASSWORD;
    private int capacity;

    private ConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_PATH));
            Class.forName(properties.getProperty("db.driver"));

            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
            capacity = Integer.parseInt(properties.getProperty("db.poolsize"));

            thePool = new HashMap<>(capacity);

            for (int i = 0; i < capacity; i++) {
                thePool.put(DriverManager.getConnection(URL, USER, PASSWORD), true);
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        for (Map.Entry<Connection, Boolean> entry : thePool.entrySet()) {
            if (entry.getValue()) {
                synchronized (this) {
                    Connection key = entry.getKey();
                    thePool.put(key, false);
                    log.debug("get connection from pool");
                    return key;
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return getConnection();
    }

    public void free(Connection connection) {
        if (thePool.containsKey(connection)) {
            thePool.put(connection, true);
            log.debug("returned connection to pool");
        } else {
            try {
                log.warn("connection was closed!!!");
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
