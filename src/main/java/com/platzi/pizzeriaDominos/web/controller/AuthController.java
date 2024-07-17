package com.platzi.pizzeriaDominos.web.controller;

import com.platzi.pizzeriaDominos.service.DTO.LoginDto;
import com.platzi.pizzeriaDominos.web.config.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
public class AuthController {

    private final JwtUtils jwtUtils;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping ("/login")
    public ResponseEntity<Void> login (@RequestBody LoginDto loginDto){
        String jwt = this.jwtUtils.create(loginDto.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();
    }

}
