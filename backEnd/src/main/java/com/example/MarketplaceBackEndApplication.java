package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example", "com.example.entity", "com.example.repository", "com.example.service", "com.example.controller", "com.example.dto"})
public class MarketplaceBackEndApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketplaceBackEndApplication.class, args);
	}
}