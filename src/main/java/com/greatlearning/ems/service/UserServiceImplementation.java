package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.repository.RoleRepository;
import com.greatlearning.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User createUser(User user) {


        User newUser = new User();
        String userName = user.getUserName();
        String password = newPasswordEncoder().encode(user.getPassword());

        newUser.setUserName(userName);
        newUser.setPassword(password);

        Set<Role> roles = user.getRoles();

        for(Role role : roles) {
            Role newRole = roleRepository.findByName(role.getName());
            if(newRole == null) {
                roleRepository.save(newRole);
            }
            newUser.addRole(newRole);
        }

        return userRepository.save(newUser);
    }

    @Bean
    public PasswordEncoder newPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
