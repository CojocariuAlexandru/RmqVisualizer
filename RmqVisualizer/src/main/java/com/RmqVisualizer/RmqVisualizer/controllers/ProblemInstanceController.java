package com.RmqVisualizer.RmqVisualizer.controllers;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.models.ProblemInstanceDto;
import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.models.UserDto;
import com.RmqVisualizer.RmqVisualizer.services.ProblemInstanceService;
import com.RmqVisualizer.RmqVisualizer.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/users/{userId}/instances/{instanceId}")
public class ProblemInstanceController {
    private ProblemInstanceService problemInstanceService;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired ProblemInstanceController(ProblemInstanceService problemInstanceService, UserService userService, ModelMapper modelMapper){
        this.problemInstanceService = problemInstanceService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Integer> getProblemInstances(@PathVariable int userIndex, @PathVariable int instanceIndex, @RequestParam int leftIndex, @RequestParam int rightIndex){
        ProblemInstance instance = userService.getInstanceByUserAndInstanceIndex(userIndex, instanceIndex);
        return new ResponseEntity<Integer>(problemInstanceService.getMinimumNumber(instance, leftIndex, rightIndex), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<ProblemInstance> createOrUpdateProblemInstance(@RequestBody ProblemInstance instance){
        ProblemInstance instanceCreated = problemInstanceService.createOrUpdateProblemInstance(instance);

        return new ResponseEntity<ProblemInstance>(instanceCreated, new HttpHeaders(), HttpStatus.CREATED);
    }

    private ProblemInstanceDto convertToDto(ProblemInstance problemInstance) {
        ProblemInstanceDto instanceDto = modelMapper.map(problemInstance, ProblemInstanceDto.class);
        return instanceDto;
    }

    private User convertToEntity(UserDto postDto) throws ParseException {
        User post = modelMapper.map(postDto, User.class);
        return post;
    }
}
