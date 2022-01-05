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

    public static final String COLUMN_SERVICE_NAME = "service_name";
    public static final String COLUMN_SERVICE_ID = "service_id";

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_SECOND_NAME = "second_name";
    public static final String COLUMN_SUR_NAME = "sur_name";
    public static final String COLUMN_EMAIL = "user_email";
    public static final String COLUMN_PHONE = "user_phone";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_ROLE = "user_role";
    public static final String COLUMN_DESC = "user_desc";

    public static final String COLUMN_CLIENT_ID = "client_id";
    public static final String COLUMN_BARBER_ID = "barber_id";
    public static final String COLUMN_BOOK_DATE = "book_date";
    public static final String COLUMN_BOOK_TIME = "book_time";
    public static final String COLUMN_BOOK_ACTIVE = "is_active";

    public static final String COLUMN_PRICE = "service_price";


    //--------------------------- ANOTHER STRINGS ---------------------------//


    //--------------------------- SELECT QUERY ---------------------------//


    //--------------------------- INSERT QUERY ---------------------------//


    //--------------------------- DELETE QUERY ---------------------------//


    //--------------------------- UPDATE QUERY ---------------------------//



    private MySQLQueryContainer() {
    }
}
