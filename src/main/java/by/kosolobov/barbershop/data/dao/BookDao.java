package by.kosolobov.barbershop.data.dao;

import by.kosolobov.barbershop.entity.Book;
import by.kosolobov.barbershop.data.sql.SQLContainer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayDeque;
import java.util.Deque;

import static by.kosolobov.barbershop.data.sql.SQLContainer.*;

public class BookDao {
    private static final Logger log = LogManager.getLogger(BookDao.class);
    private final DaoBuilder dao = DaoBuilder.BOOK_DAO;

    public Deque<Book> selectBookByClientId(String id) {
        return selectBookByUserId(COLUMN_CLIENT_ID, id);
    }

    public Deque<Book> selectBookByBarberId(String id) {
        return selectBookByUserId(SQLContainer.COLUMN_BARBER_ID, id);
    }

    public Deque<Book> selectBookByServiceId(String id) {
        return selectBookByUserId(SQLContainer.COLUMN_SERVICE_ID, id);
    }


    public Deque<Book> selectBookByUserId(String column, String id) {
        try {
            return dao.select(TABLE_BOOK, COLUMNS_BOOK)
                    .where(column, id)
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Book does not exist: {}", e.getMessage(), e);
        }
        return new ArrayDeque<>();
    }

    public Deque<Book> selectBookByDate(Date date) {
        try {
            return dao.select(TABLE_BOOK, COLUMNS_BOOK)
                    .where(COLUMN_BOOK_DATE, date.toString())
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Book does not exist: {}", e.getMessage(), e);
        }
        return new ArrayDeque<>();
    }

    public Deque<Book> selectBookByDateTime(Time time) {
        try {
            return dao.select(TABLE_BOOK, COLUMNS_BOOK)
                    .where(COLUMN_BOOK_TIME, time.toString())
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Book does not exist: {}", e.getMessage(), e);
        }
        return new ArrayDeque<>();
    }

    public boolean updateBookDateTime(Date oldDate, Time oldTime, Date newDate, Time newTime) {
        return dao.update(TABLE_BOOK)
                .set(COLUMN_BOOK_DATE, newDate.toString())
                .andSet(COLUMN_BOOK_TIME, newTime.toString())
                .where(COLUMN_BOOK_DATE, oldDate.toString())
                .andWhere(COLUMN_BOOK_TIME, oldTime.toString())
                .execute();
    }

    public boolean updateBookStatus(Date date, Time time, boolean active) {
        return dao.update(TABLE_BOOK)
                .set(COLUMN_BOOK_ACTIVE, String.valueOf(active))
                .where(COLUMN_BOOK_DATE, date.toString())
                .andWhere(COLUMN_BOOK_TIME, time.toString())
                .execute();
    }

    public boolean insertBook(int client, int barber, int service, Date date, Time time) {
        return dao.insert(TABLE_BOOK, COLUMNS_BOOK)
                .values(String.valueOf(client), String.valueOf(barber),
                        String.valueOf(service), date.toString(), time.toString(), "true")
                .execute();
    }

    public boolean deleteBook(Date date, Time time) {
        return dao.delete(TABLE_BOOK)
                .where(COLUMN_BOOK_DATE, date.toString())
                .andWhere(COLUMN_BOOK_TIME, time.toString())
                .execute();
    }

    public boolean deleteAllInactive() {
        return dao.delete(TABLE_BOOK)
                .where(COLUMN_BOOK_ACTIVE, "false")
                .execute();
    }
}
