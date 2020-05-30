package com.RmqVisualizer.RmqVisualizer.controllers;

import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.services.UserService;
import com.RmqVisualizer.RmqVisualizer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/login")
public class Login {
    @PostMapping
    public String createToken(@RequestBody User user){
        JwtUtil jwtUtil = new JwtUtil();
        return jwtUtil.generateToken(user.getName());
    }

}
