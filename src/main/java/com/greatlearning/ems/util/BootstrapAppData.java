package com.greatlearning.ems.util;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.repository.EmployeeRepository;
import com.greatlearning.ems.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class BootstrapAppData {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    public BootstrapAppData(UserRepository userRepository, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void insertAppData(ApplicationReadyEvent event) {

        //Initializing Users
//        User abhay = new User("user", this.passwordEncoder.encode("user"));
        User vamshi = new User("admin", this.passwordEncoder.encode("admin"));

        //Initializing Roles
//        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        //Only User role
//        abhay.addRole(userRole);

        //Both Admin and User role
//        vamshi.addRole(userRole);
        vamshi.addRole(adminRole);

        //Add Data To DB
//        this.userRepository.save(abhay);
        this.userRepository.save(vamshi);


        Employee abhayEmployee = new Employee("abhay", "banda", "bandaabhay@gmail.com");
        Employee vamshiEmployee = new Employee("vamshi", "dhunde", "vamshidund@gmail.com");

            employeeRepository.save(abhayEmployee);
            employeeRepository.save(vamshiEmployee);
    }
}
