package by.kosolobov.bshop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kosolobov.bshop.connector.BShopConnector;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.entity.User.UserBuilder;

public class UserDao {
    private static final Logger log = LogManager.getLogger(UserDao.class);

    /**
     * @return false if username is already exist (adding user failed), true if
     *         username is unique
     */
    public boolean addUser(String username, String password, String firstName, String secondName, String serName) {
        boolean isUnique = true;
        String getUserQuery = """
				SELECT username
				FROM users
				WHERE username = '%s';""".formatted(username);

        String addUserQuery = """
				INSERT INTO users (username, password, first_name, second_name, ser_name)
				VALUES ('%s', '%s', '%s', '%s', '%s')""".formatted(username, password, firstName, secondName, serName);

        try (Connection connection = BShopConnector.getConnection();
             Statement getStatement = connection.createStatement();
             Statement addStatement = connection.createStatement()) {

            ResultSet resultSet = getStatement.executeQuery(getUserQuery);

            if (resultSet.next()) {
                isUnique = false;
                log.log(Level.WARN, "User with username '{}' already is in database", username);
            } else {
                addStatement.execute(addUserQuery);
                log.log(Level.INFO, "User with username '{}' added to database successfully", username);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database: {}", e.getMessage());
        }

        return isUnique;
    }

    public boolean checkUser(String username, String password) {
        boolean isValid = false;
        String getUserQuery = """
				SELECT password
				FROM users
				WHERE username = '%s';""".formatted(username);

        try (Connection connection = BShopConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getUserQuery);

            if (resultSet.next()) {
                String savedPassword = resultSet.getString(1);
                isValid = password.equals(savedPassword);

            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database: {}", e.getMessage());
        }

        return isValid;
    }

    /**
     * @return false if password is incorrect or user does not exist (deleting user
     *         failed), true if user deleted successfully
     */
    public boolean deleteUser(String username, String password) {
        boolean isValid = false;
        String getUserQuery = """
				SELECT password
				FROM users
				WHERE username = '%s';""".formatted(username);

        String deleteUserQuery = """
				DELETE FROM users
				WHERE username = '%s';""".formatted(username);

        try (Connection connection = BShopConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getUserQuery);

            if (resultSet.next()) {
                String savedPassword = resultSet.getString(1);
                if (savedPassword.equals(password)) {
                    isValid = true;
                    statement.execute(deleteUserQuery);
                    log.log(Level.INFO, "User with username '{}' deleted successfully", username);
                } else {
                    log.log(Level.WARN, "Incorrect password for username {}", username);
                }
            } else {
                log.log(Level.WARN, "User with username '{}' does not exist", username);
            }

        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database: {}", e.getMessage());
        }

        return isValid;
    }

    public User getUserByLogIn(String username, String password) {
        UserBuilder builder = new UserBuilder();
        String getUserQuery = """
				SELECT *
				FROM users
				WHERE username = '%s'
				AND password = '%s'""".formatted(username, password);

        try (Connection connection = BShopConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getUserQuery);

            if (resultSet.next()) {
                configureUserBuilder(builder, resultSet);
                log.log(Level.INFO, "User with username '{}' got successfully", username);
            } else {
                log.log(Level.WARN, "User with username '{}' does not exist", username);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database: {}", e.getMessage());
        }

        return builder.build();
    }

    public User getUserById(int id) {
        UserBuilder builder = new UserBuilder();
        String getUserQuery = """
				SELECT *
				FROM users
				WHERE user_id = '%d';""".formatted(id);


        try (Connection connection = BShopConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getUserQuery);

            if (resultSet.next()) {
                configureUserBuilder(builder, resultSet);
                log.log(Level.INFO, "User-{} getted successfully", id);
            } else {
                log.log(Level.WARN, "User-{} does not exist", id);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database: {}", e.getMessage());
        }

        return builder.build();
    }

    private UserBuilder configureUserBuilder(UserBuilder builder, ResultSet resultSet) throws SQLException {
        builder.userId(resultSet.getInt(1));
        builder.username(resultSet.getString(2));
        builder.password(resultSet.getString(3));
        builder.firstName(resultSet.getString(4));
        builder.secondName(resultSet.getString(5));
        builder.serName(resultSet.getString(6));
        return builder;
    }
}







