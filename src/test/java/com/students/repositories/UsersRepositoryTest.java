package com.students.repositories;

import com.students.Application;
import com.students.entities.User;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UsersRepositoryTest {
    private static final Logger LOG = LoggerFactory.getLogger(UsersRepositoryTest.class);

    @Autowired
    Flyway flyway;

    @Before
    public void tearDown() throws Exception {
        flyway.clean();
        flyway.migrate();
    }

    @Autowired
    UsersRepository usersRepository;

    @Test
    public void findByAgeGreaterThanPositiveTest() {
        LOG.debug("[");

        List<User> users = Arrays.asList(
                new User("user", "surname", 1),
                new User("user", "surname", 2),
                new User("user", "surname", 3)
            );
        usersRepository.save(users);

        List<User> actualResult = usersRepository.findByAgeGreaterThan(2);
        LOG.debug("actualResult : {}", actualResult);

        Assert.assertEquals("More than one element in actual result!", 1, actualResult.size());
        Assert.assertEquals("Incorrect element in actual result!", users.get(2), actualResult.get(0));

        LOG.debug("]");
    }

    @Test
    public void findByAgeGreaterEmptyTest() {
        LOG.debug("[");

        List<User> users = Arrays.asList(
                new User("user", "surname", 1),
                new User("user", "surname", 2)
        );
        usersRepository.save(users);

        List<User> actualResult = usersRepository.findByAgeGreaterThan(2);
        LOG.debug("actualResult : {}", actualResult);

        Assert.assertEquals("There is element in actual result!", 0, actualResult.size());

        LOG.debug("]");
    }
}