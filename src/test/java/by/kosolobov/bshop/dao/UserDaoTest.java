package by.kosolobov.bshop.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import by.kosolobov.bshop.entity.User;

class UserDaoTest {
    private static final UserDao dao = new UserDao();

    @Test
    void testAddCheckGetDeleteUser() {
        User expected = new User("toDel", "toDel", "toDel", "toDel", "toDel");
        assertTrue(dao.addUser("toDel", "toDel", "toDel", "toDel", "toDel"));
        assertTrue(dao.checkUser("toDel", "toDel"));
        User user = dao.getUserByLogIn("toDel", "toDel");
        assertEquals(expected, user);
        assertTrue(dao.deleteUser("toDel", "toDel"));
    }
}
