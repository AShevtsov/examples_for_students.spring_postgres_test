package com.students.controllers;
import com.students.entities.User;
import com.students.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {
    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public Long createNewUser(@RequestParam  String name, @RequestParam  String surname, @RequestParam  int age){
        LOG.debug("[ createUser(name : {}, surname : {}, age : {}", name, surname, age);

        Long userId = usersService.createNewUser(name, surname, age);

        LOG.debug("] (userId : {})", userId);
        return userId;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value="id") Long id){
        LOG.info("[ getUserById : {}", id);

        User user = usersService.getUserById(id);

        LOG.info("] return : {}", user);
        return user;
    }

    @GetMapping("")
    public Iterable<User> getAllUsers(){
        LOG.info("[ getAllUsers");

        Iterable<User> users = usersService.getAllUsers();

        LOG.info("] (return : {})", users);
        return users;
    }

    @GetMapping("/older-than/{age}")
    public Iterable<User> getAllUsersOlderThan(@PathVariable(value="age") Integer age){
        LOG.info("[ getAllUsersOlderThan(age : {})", age);

        Iterable<User> users = usersService.getAllUsersOlderThan(age);

        LOG.info("] (return : {})", users);
        return users;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value="id") Long userId){
        LOG.info("[ deleteUser(userId : {})", userId);

        usersService.deleteUser(userId);

        LOG.info("]");
    }

}