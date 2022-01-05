package by.kosolobov.bshop.command.service;

import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.*;

public class UserCommandService {
    private static final Logger log = LogManager.getLogger(UserCommandService.class);
    private MainDao dao = MainDao.USER_DAO;

    public User getUserByUsername(String username) {
        try {
            Deque<User> users = dao.select(TABLE_USER, COLUMNS_USER)
                    .where("username", username)
                    .executeSql();
            return users.pop();
        } catch (SQLException e) {
            log.log(Level.WARN, "User does not exist: {}", e.getMessage(), e);
        }
        return null;
    }

    public boolean updateUserPassword(String username, String oldPass, String newPass) {
        try {
            dao.update(TABLE_USER)
                    .set("password", newPass)
                    .where("username", username)
                    .andWhere("password", oldPass)
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "User does not exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
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

        try {
            dao.execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "User does not exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean updateUserDesc(String username, String newDescription) {
        try {
            dao.update(TABLE_USER)
                    .set(COLUMN_DESC, newDescription)
                    .where(COLUMN_USERNAME, username)
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "User does not exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean insertUser(String username, String password, String userRole) {
        try {
            dao.insert(TABLE_USER, COLUMNS_USER_MIN)
                    .values(username, password, userRole)
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "This user is already exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean deleteUser(String username, String password) {
        try {
            dao.delete(TABLE_USER)
                    .where(COLUMN_USERNAME, username)
                    .andWhere(COLUMN_PASSWORD, password)
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "User does not exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }
}
