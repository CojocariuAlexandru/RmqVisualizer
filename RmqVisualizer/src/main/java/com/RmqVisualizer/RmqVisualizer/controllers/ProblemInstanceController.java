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
@RequestMapping("api/users/instances")
public class ProblemInstanceController {
    private ProblemInstanceService problemInstanceService;
    private ModelMapper modelMapper;

    @Autowired ProblemInstanceController(ProblemInstanceService problemInstanceService, ModelMapper modelMapper){
        this.problemInstanceService = problemInstanceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProblemInstance>> getProblemInstances(){
        List<ProblemInstance> instances = problemInstanceService.getAllProblemInstances();
        return new ResponseEntity<List<ProblemInstance>>(instances, new HttpHeaders(), HttpStatus.OK);
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
