package by.kosolobov.bshop.connector;

import by.kosolobov.bshop.reader.BShopPropertyReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static final Logger log = LogManager.getLogger(MySQLConnector.class);
    private static final BShopPropertyReader reader = new BShopPropertyReader();
    private static final String PROP_DRIVER = "db.driver";
    private static final String PROP_URL = "db.url";
    private static final String PROP_USERNAME = "db.username";
    private static final String PROP_PASSWORD = "db.password";

    static {
        try {
            Class.forName(reader.get(PROP_DRIVER));
        } catch (ClassNotFoundException e) {
            log.log(Level.FATAL, "MySQL driver \"{}\" not found: {}", reader.get(PROP_DRIVER), e.getMessage(), e);
        }
    }

    private MySQLConnector() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(reader.get(PROP_URL), reader.get(PROP_USERNAME), reader.get(PROP_PASSWORD));
    }
}