package com.RmqVisualizer.RmqVisualizer.controllers;

import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> players = userService.getAllUsers();
        return new ResponseEntity<List<User>>(players, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<User> createOrUpdatePlayer(@RequestBody User user){
        User usersCreated = userService.createOrUpdatePlayer(user);
        return new ResponseEntity<User>(usersCreated, new HttpHeaders(), HttpStatus.CREATED);
    }

}
