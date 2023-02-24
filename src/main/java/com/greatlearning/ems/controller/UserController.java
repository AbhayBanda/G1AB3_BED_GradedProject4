package com.greatlearning.ems.controller;

import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserServiceImplementation userServiceImplementation;

    @GetMapping("/users")
    List<User> getAllUser() {
        return userServiceImplementation.listAllUsers();
    }

    @PostMapping("/users/add")
    User addUserRecord(@RequestBody User user) {
        return userServiceImplementation.createUser(user);
    }
}
