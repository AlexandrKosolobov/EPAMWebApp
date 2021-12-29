package by.kosolobov.bshop.sql;

public final class BShopQueryContainer {
    //--------------------------- SELECT QUERY ---------------------------//
    public static final String SQL_FIND_USER_BY_ID = "SELECT user_id, username, password,  first_name, second_name, sur_name FROM users WHERE user_id = ?;";
    public static final String SQL_FIND_USER_BY_USERNAME = "SELECT user_id, username, password, first_name, second_name, sur_name FROM users WHERE username = ?;";
    public static final String SQL_FIND_USER_BY_FIRST_NAME = "SELECT user_id, username, password,  first_name, second_name, sur_name FROM users WHERE first_name = ?;";
    public static final String SQL_FIND_USER_BY_SECOND_NAME = "SELECT user_id, username, password,  first_name, second_name, sur_name FROM users WHERE second_name = ?;";
    public static final String SQL_FIND_USER_BY_SUR_NAME = "SELECT user_id, username, password,  first_name, second_name, sur_name FROM users WHERE sur_name = ?;";
    public static final String SQL_GET_USER_PASSWORD = "SELECT password FROM users WHERE username = ?;";
    public static final String SQL_GET_USER_ID = "SELECT user_id FROM users WHERE username = ?;";

    //--------------------------- INSERT QUERY ---------------------------//

    public static final String SQL_ADD_USER = "INSERT INTO users (username, password, first_name, second_name, sur_name) VALUES (?, ?, ?, ?, ?);";

    //--------------------------- DELETE QUERY ---------------------------//


    //--------------------------- UPDATE QUERY ---------------------------//

    private BShopQueryContainer() {
    }
}
