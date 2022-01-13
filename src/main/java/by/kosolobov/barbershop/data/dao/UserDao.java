package by.kosolobov.barbershop.data.dao;

import by.kosolobov.barbershop.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

import static by.kosolobov.barbershop.data.sql.SQLContainer.*;

public class UserDao {
    private static final Logger log = LogManager.getLogger(UserDao.class);
    private static final String BARBER_ROLE = "barber";
    private final DaoBuilder dao = DaoBuilder.USER_DAO;

    public Deque<User> selectAllBarbers() {
        try {
            return dao.select(TABLE_USER)
                    .where(COLUMN_USER_ROLE, BARBER_ROLE)
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.ERROR, "SELECT: User does not exist: {}", e.getMessage(), e);
        }
        return new ArrayDeque<>();
    }

    public Optional<User> selectUserByUsername(String username) {
        Optional<User> res;
        try {
            return Optional.of( (User) dao.select(TABLE_USER, COLUMNS_USER)
                    .where(COLUMN_USERNAME, username)
                    .executeSql()
                    .pop());
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: User does not exist: {}", e.getMessage(), e);
        }

        return Optional.empty();
    }

    public Optional<String> selectPassword(String username) {
        List<Map<String, String>> entities = dao.select(TABLE_USER, COLUMN_PASSWORD)
                .where(COLUMN_USERNAME, username)
                .executeRow(COLUMN_PASSWORD);
        if (!entities.isEmpty()) {
            return Optional.of(entities.get(0).get(COLUMN_PASSWORD));
        } else {
            log.log(Level.ERROR, "User {} has no password!", username);
            return Optional.empty();
        }
    }

    public boolean updatePassword(String username, String oldPass, String newPass) {
        return dao.update(TABLE_USER)
                .set(COLUMN_PASSWORD, newPass)
                .where(COLUMN_USERNAME, username)
                .andWhere(COLUMN_PASSWORD, oldPass)
                .execute();
    }

    public boolean updateUsername(String oldUsername, String newUsername) {
        return updateUser(oldUsername, COLUMN_USERNAME, newUsername);
    }

    public boolean updateFirstName(String username, String first) {
        return updateUser(username, COLUMN_FIRST_NAME, first);
    }

    public boolean updateSecondName(String username, String second) {
        return updateUser(username, COLUMN_SECOND_NAME, second);
    }

    public boolean updateSurName(String username, String sur) {
        return updateUser(username, COLUMN_SUR_NAME, sur);
    }

    public boolean updateEmail(String username, String email) {
        return updateUser(username, COLUMN_EMAIL, email);
    }

    public boolean updatePhone(String username, String phone) {
        return updateUser(username, COLUMN_PHONE, phone);
    }

    public boolean updateUserDesc(String username, String desc) {
        return updateUser(username, COLUMN_DESC, desc);
    }

    private boolean updateUser(String username, String column, String value) {
        return dao.update(TABLE_USER)
                .set(column, value)
                .where(COLUMN_USERNAME, username)
                .execute();
    }

    public boolean insertUser(String username, String password, String userRole) {
        return dao.insert(TABLE_USER, COLUMNS_USER_MIN)
                .values(username, password, userRole)
                .execute();
    }

    public boolean deleteUser(String username, String password) {
        return dao.delete(TABLE_USER)
                .where(COLUMN_USERNAME, username)
                .andWhere(COLUMN_PASSWORD, password)
                .execute();
    }
}
