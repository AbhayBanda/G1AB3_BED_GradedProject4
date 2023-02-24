package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class DomainUserDetailsServiceImpl<UserJpaRepository> implements UserDetailsService {
    private final UserRepository userRepository;

    public DomainUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Entered username is " + username);
        // Search for the user in the database and return the user
        Optional<User> optionalUser = this.userRepository.findByUserName(username);

        if (optionalUser.isPresent()) {
            System.out.println("User found with the given username");
            return new DomainUserDetails(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("invalid username");
        }
    }
}
