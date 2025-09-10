package com.example.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.dto.JwtResponse;
import com.example.dto.LoginRequest;
import com.example.dto.SignupRequest;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.JwtUtils;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public String register(@RequestBody SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Error: Username already taken!";
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Error: Email already in use!";
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : "USER") 
                .build();

        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtUtils.generateToken(request.getUsername());

        return new JwtResponse(token, "Bearer", request.getUsername());
    }

    @PostMapping("/logout")
    public String logout() {
        // For stateless JWT, logout is handled client-side (delete token).
        return "Logout successful (remove token client-side).";
    }
}
