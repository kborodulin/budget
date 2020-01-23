package ru.innopolis.budget;

import org.junit.Test;
import ru.innopolis.db.dao.UserDaoImpl;
import ru.innopolis.db.model.User;

public class UserDaoImplTest {
    private User user = new User();
    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void task_4bTest() {
        user.setUsername("qqq1");
        user.setEmail("qqq@qqq1");
        user.setPassword("1");
        userDao.addUser(user);
    }
}
