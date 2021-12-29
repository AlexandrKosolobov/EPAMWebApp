package by.kosolobov.bshop.connector;

import by.kosolobov.bshop.reader.BShopPropertyReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BShopConnector {
    private static final Logger log = LogManager.getLogger(BShopConnector.class);
    private static final BShopPropertyReader reader = new BShopPropertyReader();

    static {
        try {
            Class.forName(reader.get("db.driver"));
        } catch (ClassNotFoundException e) {
            log.log(Level.FATAL, "MySQL driver class not found: {}", e.getMessage());
        }
    }

    private BShopConnector() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(reader.get("db.url"), reader.get("db.username"), reader.get("db.password"));
    }
}