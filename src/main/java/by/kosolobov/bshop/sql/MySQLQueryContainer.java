package by.kosolobov.bshop.sql;

public final class MySQLQueryContainer {
    //--------------------------- TABLES ---------------------------//
    public static final String TABLE_USER = "users";
    public static final String TABLE_SERVICE = "services";
    public static final String TABLE_BOOK = "books";
    public static final String TABLE_CERTIFICATE = "certificates";
    public static final String TABLE_BARBER_HAS_SERVICE = "barbers_has_services";

    //--------------------------- COLUMNS ---------------------------//
    public static final String[] COLUMNS_USER = {"user_id", "username", "password", "user_role",
            "first_name", "second_name", "sur_name", "user_email", "user_phone", "user_desc"};
    public static final String[] COLUMNS_USER_MIN = {"username", "password", "user_role"};
    public static final String[] COLUMNS_SERVICE = {"service_id", "service_name"};
    public static final String[] COLUMNS_BOOK = {"client_id", "barber_id", "service_id",
            "book_date", "book_time", "is_active"};
    public static final String[] COLUMNS_CERTIFICATE = {"barber_id", "certificate_file_name", "certificate_checked"};
    public static final String[] COLUMNS_BARBER_HAS_SERVICE = {"barber_id", "service_id", "service_price"};

    //--------------------------- ANOTHER STRINGS ---------------------------//


    //--------------------------- SELECT QUERY ---------------------------//


    //--------------------------- INSERT QUERY ---------------------------//


    //--------------------------- DELETE QUERY ---------------------------//


    //--------------------------- UPDATE QUERY ---------------------------//



    private MySQLQueryContainer() {
    }
}
