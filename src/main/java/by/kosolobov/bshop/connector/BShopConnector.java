package by.kosolobov.bshop.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.kosolobov.bshop.container.DataBaseStringContainer.*;

public class BShopConnector {
    private static final Logger log = LogManager.getLogger(BShopConnector.class);

//    static {
//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//        } catch (SQLException e) {
//            log.log(Level.FATAL, "Failed registering driver: {}", e.getMessage());
//        }
//    }

    private BShopConnector() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ROOT_URL, ROOT_USER, ROOT_PASSWORD);
    }
}