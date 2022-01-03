package by.kosolobov.bshop.connector;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger log = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final BlockingQueue<Connection> FREE_POOL = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
    private static final BlockingQueue<Connection> USE_POOL = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
    private static ConnectionPool instance = new ConnectionPool();
    private static final ReentrantLock locker = new ReentrantLock();

    private ConnectionPool() {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                    FREE_POOL.put(MySQLConnector.getConnection());
            }
            log.log(Level.INFO, "Connection pool initialization successful");
        } catch (SQLException e) {
            log.log(Level.ERROR, "Connection pool initialization error: {}", e.getMessage(), e);
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "Connection pool initialization interrupted: {}", e.getMessage(), e);
        }
    }

    /**
     * Double-check Singleton
     * @return Connection pool
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
            locker.unlock();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = FREE_POOL.take();
            USE_POOL.put(connection);
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "Getting connection from connection pool interrupted: {}", e.getMessage(), e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            FREE_POOL.put(connection);
            if (!USE_POOL.remove(connection)) {
                log.log(Level.WARN, "Illegal operation! Can not release connection that is not in use!");
            }
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "Realising connection interrupted: {}", e.getMessage(), e);
        }
    }

    public void destroy() {
        try {
            for (Connection c : FREE_POOL) {
                c.close();
            }
            FREE_POOL.clear();

            for (Connection c : USE_POOL) {
                c.close();
            }
            USE_POOL.clear();

            log.log(Level.INFO, "Destroying connection pool successful");
        } catch (SQLException e) {
            log.log(Level.WARN, "Destroying connection pool failed: {}", e.getMessage(), e);
        }
    }
}
