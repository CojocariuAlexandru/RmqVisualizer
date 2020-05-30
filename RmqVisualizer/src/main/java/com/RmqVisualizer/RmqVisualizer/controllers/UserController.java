package com.RmqVisualizer.RmqVisualizer.controllers;

import com.RmqVisualizer.RmqVisualizer.models.Association;
import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.models.UserDto;
import com.RmqVisualizer.RmqVisualizer.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = userService.getAllUsers();
        List<UserDto> usersDTO = users.stream()
                                      .map(this::convertToDto)
                                      .collect(Collectors.toList());
        logger.info("All users returned successfully");
        return new ResponseEntity<List<UserDto>>(usersDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<User> createOrUpdateUser(@RequestBody User user){
        User usersCreated = userService.createOrUpdateUser(user.getName(), user.getProblemInstanceList());
        logger.info("User created")
        return new ResponseEntity<User>(usersCreated, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PatchMapping
    public void addProblemInstanceToUser(@RequestBody Association association){
        logger.info("User and problem instance associated");
        userService.assignUserToProblemInstance(association.getUserId(), association.getProblemId());
    }

    private UserDto convertToDto(User user) {
        UserDto postDto = modelMapper.map(user, UserDto.class);
        return postDto;
    }

    private User convertToEntity(UserDto postDto) throws ParseException {
        User post = modelMapper.map(postDto, User.class);
        return post;
    }

}
