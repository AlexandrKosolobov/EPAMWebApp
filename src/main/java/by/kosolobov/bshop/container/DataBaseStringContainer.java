package by.kosolobov.bshop.container;

public class DataBaseStringContainer {
    // Table names of barbershop database
    public static final String BARBER_TABLE = "barbers";
    public static final String CLIENT_TABLE = "clients";
    public static final String USER_TABLE = "users";
    public static final String BOOK_TABLE = "books";
    public static final String SERVICE_TABLE = "services";
    public static final String BARBER_SERVICES_TABLE = "services_barbers";

    // Name of database
    public static final String DBNAME = "barbershop";

    // User parameters for connecting to database
    public static final String ROOT_URL = "jdbc:mysql://localhost:3306/barbershop";
    public static final String ROOT_USER = "root";
    public static final String ROOT_PASSWORD = "root";

    // All others constants
    public static final String ALL = "*";

    private DataBaseStringContainer() {
    }
}
