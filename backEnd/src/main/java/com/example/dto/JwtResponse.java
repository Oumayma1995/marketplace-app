package com.example.dto;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
}