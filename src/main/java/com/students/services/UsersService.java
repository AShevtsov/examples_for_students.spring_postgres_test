package com.students.services;

import com.students.entities.User;
import com.students.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository usersRepository;

    public Long createNewUser(String name, String surname, int age){
        LOG.debug("[ createUser(name : {}, surname : {}, age : {}", name, surname, age);

        User user = new User(name, surname, age);
        usersRepository.save(user);

        LOG.debug("] (userId : {})", user.getUserId());
        return user.getUserId();
    }

    public User getUserById(Long userId){
        LOG.debug("[ getById(userId : {})", userId);

        User user = usersRepository.findOne(userId);

        LOG.debug("] (return : {})", user);
        return user;
    }

    public Iterable<User> getAllUsers(){
        LOG.debug("[ getAllUsers");

        Iterable<User> users = usersRepository.findAll();

        LOG.debug("] (return : {})", users);
        return users;
    }

    public Iterable<User> getAllUsersOlderThan(Integer age){
        LOG.debug("[ getAllUsersOlderThan(age : {})", age);

        Iterable<User> users = usersRepository.findByAgeGreaterThan(age);

        LOG.debug("] (return : {})", users);
        return users;
    }

    public void deleteUser(Long userId){
        LOG.debug("[ deleteUser(userId : {})", userId);

        usersRepository.delete(userId);

        LOG.debug("]");
    }

}
