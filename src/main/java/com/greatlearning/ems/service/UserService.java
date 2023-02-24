package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.User;

import java.util.List;

public interface UserService {
    List<User> listAllUsers();
    User createUser(User user);
}
