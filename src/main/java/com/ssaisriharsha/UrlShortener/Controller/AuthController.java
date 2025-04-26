package com.ssaisriharsha.UrlShortener.Controller;

import com.ssaisriharsha.UrlShortener.Entities.AppUser;
import com.ssaisriharsha.UrlShortener.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService service) {
        this.authService=service;
    }
    @PostMapping("/api/signup")
    public ResponseEntity<Map<String, String>> handleUserCreation(@RequestBody AppUser user) {
        authService.createUser(user);
        Map<String, String> map=new HashMap<>();
        map.put("message", "User created successfully. You may proceed to login now.");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
