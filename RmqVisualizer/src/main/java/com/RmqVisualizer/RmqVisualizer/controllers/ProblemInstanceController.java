package com.RmqVisualizer.RmqVisualizer.controllers;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.models.ProblemInstanceDto;
import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.models.UserDto;
import com.RmqVisualizer.RmqVisualizer.services.ProblemInstanceService;
import com.RmqVisualizer.RmqVisualizer.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.parsing.Problem;
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
@RequestMapping("api/instances")
public class ProblemInstanceController {
    private ProblemInstanceService problemInstanceService;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public ProblemInstanceController(ProblemInstanceService problemInstanceService, UserService userService, ModelMapper modelMapper){
        this.problemInstanceService = problemInstanceService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @RequestMapping("api/users/{userIndex}/instances/{instanceIndex}/result")
    @GetMapping
    public ResponseEntity<Integer> getProblemResult(@PathVariable int userIndex, @PathVariable int instanceIndex, @RequestParam int leftIndex, @RequestParam int rightIndex){
        ProblemInstance instance = userService.getInstanceByUserAndInstanceIndex(userIndex, instanceIndex);
        return new ResponseEntity<Integer>(problemInstanceService.getMinimumNumber(instance, leftIndex, rightIndex), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping("api/users/{userIndex}/instances/{instanceIndex}")
    @GetMapping
    public ResponseEntity<ProblemInstanceDto> getProblemInstance(@PathVariable int userIndex, @PathVariable int instanceIndex){
        ProblemInstance instance = userService.getInstanceByUserAndInstanceIndex(userIndex, instanceIndex);
        ProblemInstanceDto instanceDto = convertToDto(instance);
        return new ResponseEntity<ProblemInstanceDto>(instanceDto, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ProblemInstance> createOrUpdateProblemInstance(@RequestBody ProblemInstanceDto instance){
        ProblemInstance instanceCreated = convertToEntity(instance);
        problemInstanceService.createOrUpdateProblemInstance(instanceCreated);
        return new ResponseEntity<ProblemInstance>(instanceCreated, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProblemInstance>> getAllProblemInstances(){
        List<ProblemInstance> instances = problemInstanceService.getAllProblemInstances();
        return new ResponseEntity<List<ProblemInstance>>(instances, new HttpHeaders(), HttpStatus.OK);
    }

    private ProblemInstanceDto convertToDto(ProblemInstance problemInstance) {
        ProblemInstanceDto instanceDto = modelMapper.map(problemInstance, ProblemInstanceDto.class);
        return instanceDto;
    }

    private ProblemInstance convertToEntity(ProblemInstanceDto postDto){
        ProblemInstance instance = modelMapper.map(postDto, ProblemInstance.class);
        return instance;
    }
}
