package com.greatlearning.ems.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //  To Setup Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http
                .authorizeRequests().antMatchers(
                        HttpMethod.GET,
                        "/api/employees/**",
                     "/api/employees**"
                     )
                     .hasAnyRole("USER", "ADMIN")
                     .antMatchers(
                             HttpMethod.POST,
                             "/api/employees/**", "/api/employees**"
                     )
                     .hasRole("ADMIN")
                     .antMatchers(
                             HttpMethod.PUT,
                             "/api/employees/**", "/api/employees**"
                     )
                     .hasRole("ADMIN")
                     .antMatchers(
                             HttpMethod.DELETE,
                             "/api/employees/**", "/api/employees**"
                     )
                     .hasRole("ADMIN")
                     .antMatchers("/api/roles/**", "/api/roles**")
                     .hasRole("ADMIN")
                     .antMatchers("/api/users/**", "/api/users**")
                     .hasRole("ADMIN")
                .and()
                .httpBasic();
        http.csrf().disable().cors().disable();
        http.headers().frameOptions().disable();
    }

    //  To Setup Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
