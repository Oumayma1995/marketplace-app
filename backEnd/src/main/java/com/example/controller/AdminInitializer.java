package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.entity.User;
import com.example.repository.UserRepository;
import jakarta.annotation.PostConstruct;

//@Component
public class AdminInitializer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("adminpassword"))
                    .role("ADMIN")
                    .build();
            userRepository.save(admin);
        }
    }
}
