package by.kosolobov.bshop.connector;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static final Logger log = LogManager.getLogger(ConnectionPool.class);
    private static final List<Connection> pool = new ArrayList<>();
    private static final ConnectionPool instance = new ConnectionPool();
    private static final int DEFAULT_POOL_SIZE = 8;
    private static int currentPoolSizeLimit = DEFAULT_POOL_SIZE;

    private ConnectionPool() {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                    pool.add(BShopConnector.getConnection());
            }
            log.log(Level.INFO, "Connection pool initialization successful");
        } catch (SQLException e) {
            log.log(Level.ERROR, "Connection pool initialization error: {}", e.getMessage());
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        if (pool.isEmpty()) {
            currentPoolSizeLimit++;
            log.log(Level.WARN, "There are no free connections, creating new one. Current pool size = {}", currentPoolSizeLimit);
            try {
                return BShopConnector.getConnection();
            } catch (SQLException e) {
                log.log(Level.ERROR, "Creating new connection failed: {}", e.getMessage());
            }

            return null;
        }

        return pool.remove(0);
    }

    public void releaseConnection(Connection connection) {
        try {
            if (connection.isClosed()) {
                pool.add(BShopConnector.getConnection());
            } else {
                pool.add(connection);
            }
        } catch (SQLException e) {
            log.log(Level.WARN, "Closed connection released, creating new connection: {}", e.getMessage());
        }
    }

    public void destroy() {
        try {
            for (Connection c : pool) {
                c.close();
                pool.remove(c);
            }
            log.log(Level.INFO, "Destroying connection pool successful");
        } catch (SQLException e) {
            log.log(Level.WARN, "Destroying connection pool failed");
        }
    }
}
