package by.kosolobov.bshop.dao;

import java.sql.*;

import by.kosolobov.bshop.connector.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.entity.User.UserBuilder;

import static by.kosolobov.bshop.sql.BShopQueryContainer.*;

public class UserDao {
    private static final Logger log = LogManager.getLogger(UserDao.class);
    private Connection connection = null;

    /**
     * @return false if username is already exist (adding user failed), true if
     *         username is unique
     */
    public boolean addUser(String username, String password, String firstName, String secondName, String serName) {
        connection = ConnectionPool.getInstance().getConnection();
        boolean isUnique = false;

        try (PreparedStatement addUserStatement = connection.prepareStatement(SQL_ADD_USER);
             PreparedStatement getUserStatement = connection.prepareStatement(SQL_GET_USER_ID)) {

            getUserStatement.setString(1, username);

            ResultSet checkSet = getUserStatement.executeQuery();
            isUnique = !checkSet.next();
            checkSet.close();

            if (isUnique) {
                addUserStatement.setString(1, username);
                addUserStatement.setString(2, password);
                addUserStatement.setString(3, firstName);
                addUserStatement.setString(4, secondName);
                addUserStatement.setString(5, serName);
                addUserStatement.execute();

                log.log(Level.INFO, "User with username '{}' added to database successfully", username);
            } else {
                log.log(Level.WARN, "User with username '{}' already is in database", username);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database, adding user failed: {}", e.getMessage());
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return isUnique;
    }

    public boolean checkUser(String username, String enteredPassword) {
        connection = ConnectionPool.getInstance().getConnection();
        boolean isValid = false;

        try (PreparedStatement getUserStatement = connection.prepareStatement(SQL_FIND_USER_BY_USERNAME)) {
            getUserStatement.setString(1, username);
            ResultSet resultSet = getUserStatement.executeQuery();
            
            if (resultSet.next()) {
                String savedPassword = resultSet.getString(3);
                isValid = enteredPassword.equals(savedPassword);
            } else {
                log.log(Level.INFO, "Log In user {} failed, incorrect password", username);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database, checking user failed: {}", e.getMessage());
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return isValid;
    }

    /**
     * @return false if password is incorrect or user does not exist (deleting user
     *         failed), true if user deleted successfully
     */
    public boolean deleteUser(String username, String password) {
        connection = ConnectionPool.getInstance().getConnection();
        boolean isValid = false;
        String getUserQuery = """
				SELECT password
				FROM users
				WHERE username = '%s';""".formatted(username);

        String deleteUserQuery = """
				DELETE FROM users
				WHERE username = '%s';""".formatted(username);

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getUserQuery)) {

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
            log.log(Level.ERROR, "Have no access to database, deleting user failed: {}", e.getMessage());
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return isValid;
    }

    public User getUserByLogIn(String username, String password) {
        connection = ConnectionPool.getInstance().getConnection();
        UserBuilder builder = new UserBuilder();
        String getUserQuery = """
				SELECT *
				FROM users
				WHERE username = '%s'
				AND password = '%s'""".formatted(username, password);

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getUserQuery)) {

            if (resultSet.next()) {
                configureUserBuilder(builder, resultSet);
                log.log(Level.INFO, "User with username '{}' got successfully", username);
            } else {
                log.log(Level.WARN, "User with username '{}' does not exist", username);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database, getting user failed: {}", e.getMessage());
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return builder.build();
    }

    public User getUserById(int id) {
        connection = ConnectionPool.getInstance().getConnection();
        UserBuilder builder = new UserBuilder();
        String getUserQuery = """
				SELECT *
				FROM users
				WHERE user_id = '%d';""".formatted(id);


        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getUserQuery)) {

            if (resultSet.next()) {
                configureUserBuilder(builder, resultSet);
                log.log(Level.INFO, "User-{} got successfully", id);
            } else {
                log.log(Level.WARN, "User-{} does not exist", id);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "Have no access to database, getting user failed: {}", e.getMessage());
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return builder.build();
    }

    private UserBuilder configureUserBuilder(UserBuilder builder, ResultSet resultSet) throws SQLException {
        builder.userId(resultSet.getInt(1));
        builder.username(resultSet.getString(2));
        builder.password(resultSet.getString(3));
        builder.firstName(resultSet.getString(4));
        builder.secondName(resultSet.getString(5));
        builder.surName(resultSet.getString(6));
        return builder;
    }
}







