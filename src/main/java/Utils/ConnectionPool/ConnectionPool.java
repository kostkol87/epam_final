package Utils.ConnectionPool;

import Utils.DBUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionPool {

    public static final String PROPERTIES_PATH = "pool.properties";
    private static ConnectionPool INSTANCE;

    public static ConnectionPool getInstance() {

        if (INSTANCE == null)
            synchronized (ConnectionPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            }

        return INSTANCE;
    }

    private int capacity;
    private HashMap<Connection, Boolean> thePool;

    /**
     * initializing with properties
     */
    private ConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_PATH));
            capacity = Integer.parseInt(properties.getProperty("db.poolsize"));
            thePool = new HashMap<>(capacity);
            for (int i = 0; i < capacity; i++) {
                thePool.put(DBUtils.getCon(), true);
            }

        } catch (IOException  e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        for (Map.Entry<Connection, Boolean> entry : thePool.entrySet()){
            if (entry.getValue()){
                synchronized (this){
                    Connection key = entry.getKey();
                    thePool.put(key, false);
                    return key;
                }
            }
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return getConnection();
    }

    public void free(Connection connection){
        if (thePool.containsKey(connection)){
            thePool.put(connection, true);
        }else {
//            log.warn("Connection does not from pool!!! it was simply closed");
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
