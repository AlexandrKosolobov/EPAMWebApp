package by.kosolobov.bshop.command.service;

import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.Book;
import by.kosolobov.bshop.sql.MySQLQueryContainer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayDeque;
import java.util.Deque;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.*;

public class BookCommandService {
    private static final Logger log = LogManager.getLogger(BookCommandService.class);
    private final MainDao dao = MainDao.BOOK_DAO;

    public Deque<Book> getBookByClientId(String id) {
        return getBookByUserId(COLUMN_CLIENT_ID, id);
    }

    public Deque<Book> getBookByBarberId(String id) {
        return getBookByUserId(MySQLQueryContainer.COLUMN_BARBER_ID, id);
    }

    public Deque<Book> getBookByServiceId(String id) {
        return getBookByUserId(MySQLQueryContainer.COLUMN_SERVICE_ID, id);
    }


    public Deque<Book> getBookByUserId(String column, String id) {
        try {
            return dao.select(TABLE_BOOK, COLUMNS_BOOK)
                    .where(column, id)
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Book does not exist: {}", e.getMessage(), e);
        }
        return new ArrayDeque<>();
    }

    public Deque<Book> getBookByDate(Date date) {
        try {
            return dao.select(TABLE_BOOK, COLUMNS_BOOK)
                    .where(COLUMN_BOOK_DATE, date.toString())
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Book does not exist: {}", e.getMessage(), e);
        }
        return new ArrayDeque<>();
    }

    public Book getBookByDateTime(Time time) {
        try {
            return (Book) dao.select(TABLE_BOOK, COLUMNS_BOOK)
                    .where(COLUMN_BOOK_TIME, time.toString())
                    .executeSql()
                    .pop();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Book does not exist: {}", e.getMessage(), e);
        }
        return null;
    }

    public boolean updateBookDateTime(Date oldDate, Time oldTime, Date newDate, Time newTime) {
        try {
            dao.update(TABLE_BOOK)
                    .set(COLUMN_BOOK_DATE, newDate.toString())
                    .andSet(COLUMN_BOOK_TIME, newTime.toString())
                    .where(COLUMN_BOOK_DATE, oldDate.toString())
                    .andWhere(COLUMN_BOOK_TIME, oldTime.toString())
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "UPDATE: Book does not exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean updateBookStatus(Date date, Time time, boolean active) {
        try {
            dao.update(TABLE_BOOK)
                    .set(COLUMN_BOOK_ACTIVE, String.valueOf(active))
                    .where(COLUMN_BOOK_DATE, date.toString())
                    .andWhere(COLUMN_BOOK_TIME, time.toString())
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "UPDATE: Book does not exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean insertBook(int client, int barber, int service, Date date, Time time) {
        try {
            dao.insert(TABLE_BOOK, COLUMNS_BOOK)
                    .values(String.valueOf(client), String.valueOf(barber),
                            String.valueOf(service), date.toString(), time.toString(), "true")
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "INSERT: Book is already exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean deleteBook(Date date, Time time) {
        try {
            dao.delete(TABLE_BOOK)
                    .where(COLUMN_BOOK_DATE, date.toString())
                    .andWhere(COLUMN_BOOK_TIME, time.toString())
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "DELETE: Book does not exist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    public boolean deleteAllInactive() {
        try {
            dao.delete(TABLE_BOOK)
                    .where(COLUMN_BOOK_ACTIVE, "false")
                    .execute();
        } catch (SQLException e) {
            log.log(Level.WARN, "DELETE: Books does not exist");
            return false;
        }
        return true;
    }
}
