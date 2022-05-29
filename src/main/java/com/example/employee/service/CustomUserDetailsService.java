package com.example.employee.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${test.username}")
    private String USERNAME;

    @Value("${test.password}")
    private String PASSWORD;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User hardCodedUser = new User(USERNAME, PASSWORD, new ArrayList<>());
        if (!hardCodedUser.getUsername().equals(username)) {
            throw new UsernameNotFoundException("Incorrect Credentials.");
        }
        return hardCodedUser;
    }
}
