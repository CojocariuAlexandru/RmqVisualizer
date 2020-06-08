package com.RmqVisualizer.RmqVisualizer.controllers;

import com.RmqVisualizer.RmqVisualizer.models.*;
import com.RmqVisualizer.RmqVisualizer.services.ProblemInstanceService;
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
    private ProblemInstanceService instanceService;
    private ModelMapper modelMapper;
    private Logger logger;

    @Autowired
    public UserController(UserService userService, ProblemInstanceService instanceService, ModelMapper modelMapper){
        this.userService = userService;
        this.instanceService = instanceService;
        this.modelMapper = modelMapper;
        this.logger = LoggerFactory.getLogger(UserController.class);
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
    public ResponseEntity<UserDto> createOrUpdateUser(@RequestBody UserDto user){
        User userCreated = userService.createOrUpdateUser(user.getName());
        UserDto userCreatedDto = convertToDto(userCreated);
        logger.info("User created");
        return new ResponseEntity<UserDto>(userCreatedDto, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PatchMapping
    public void addProblemInstanceToUser(@RequestBody Association association){
        logger.info("User and problem instance associated");
        userService.assignUserToProblemInstance(association.getUserId(), association.getProblemId());
    }

    @RequestMapping("/{userIndex}/instances/{instanceIndex}/result")
    @GetMapping
    public ResponseEntity<Integer> getProblemResult(@PathVariable int userIndex, @PathVariable int instanceIndex, @RequestParam int leftIndex, @RequestParam int rightIndex){
        ProblemInstance instance = userService.getInstanceByUserAndInstanceIndex(userIndex, instanceIndex);
        return new ResponseEntity<Integer>(instanceService.getMinimumNumber(instance, leftIndex, rightIndex), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping("/{userIndex}/instances/{instanceIndex}")
    @GetMapping
    public ResponseEntity<ProblemInstanceDto> getProblemInstance(@PathVariable int userIndex, @PathVariable int instanceIndex){
        ProblemInstance instance = userService.getInstanceByUserAndInstanceIndex(userIndex, instanceIndex);
        ProblemInstanceDto instanceDto = convertToDto(instance);
        return new ResponseEntity<ProblemInstanceDto>(instanceDto, new HttpHeaders(), HttpStatus.CREATED);
    }

    private UserDto convertToDto(User user) {
        UserDto postDto = modelMapper.map(user, UserDto.class);
        return postDto;
    }

    private ProblemInstanceDto convertToDto(ProblemInstance instance){
        ProblemInstanceDto postDto = modelMapper.map(instance, ProblemInstanceDto.class);
        return postDto;
    }

    private User convertToEntity(UserDto postDto) throws ParseException {
        User post = modelMapper.map(postDto, User.class);
        return post;
    }

}
