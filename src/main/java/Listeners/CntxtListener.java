package Listeners;

import Utils.ConnectionPool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CntxtListener implements ServletContextListener {
    /**
     * Here ConnectionPool is initializing, to escape freezes later...
     */

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }
}
