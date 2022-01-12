package by.kosolobov.bshop.service;

import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.mapper.UserPasswordPair;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.*;

public class UserCommandService {
    private static final Logger log = LogManager.getLogger(UserCommandService.class);
    private static final String BARBER_ROLE = "barber";
    private final MainDao dao = MainDao.USER_DAO;

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

    public User selectUserByUsername(String username) {
        try {
            return (User) dao.select(TABLE_USER, COLUMNS_USER)
                    .where(COLUMN_USERNAME, username)
                    .executeSql()
                    .pop();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: User does not exist: {}", e.getMessage(), e);
        }
        return null;
    }

    public String selectUserPassword(User user) {
        Optional<List<Map<String, String>>> optional = dao.select(TABLE_USER, COLUMN_PASSWORD)
                .where(COLUMN_USERNAME, user.getUsername())
                .executeRow(COLUMN_PASSWORD);
        if (optional.isPresent()) {
            List<Map<String, String>> list = optional.get();
            if (list.isEmpty()) {
                return null;
            }
            Map<String, String> map = list.get(0);
            if (map.isEmpty()) {
                return null;
            }
            return map.get(COLUMN_PASSWORD);
        } else {
            log.log(Level.ERROR, "User {} has no password!", user.getUsername());
            return null;
        }
    }

    public boolean updateUserPassword(String username, String oldPass, String newPass) {
        return dao.update(TABLE_USER)
                .set("password", newPass)
                .where("username", username)
                .andWhere("password", oldPass)
                .execute();
    }

    public boolean updateUserInfo(String username, String first, String second, String sur, String email, String phone) {
        dao.update(TABLE_USER);
        dao.set(COLUMN_USERNAME, username);

        if (first != null) {
            dao.andSet(COLUMN_FIRST_NAME, first);
        }
        if (second != null) {
            dao.andSet(COLUMN_SECOND_NAME, second);
        }
        if (sur != null) {
            dao.andSet(COLUMN_SUR_NAME, sur);
        }
        if (email != null) {
            dao.andSet(COLUMN_EMAIL, email);
        }
        if (phone != null) {
            dao.andSet(COLUMN_PHONE, phone);
        }

        dao.where(COLUMN_USERNAME, username);

        return dao.execute();
    }

    public boolean updateUserDesc(String username, String newDescription) {
        return dao.update(TABLE_USER)
                .set(COLUMN_DESC, newDescription)
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
