package com.RmqVisualizer.RmqVisualizer.controllers;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.services.ProblemInstanceService;
import com.RmqVisualizer.RmqVisualizer.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instances")
public class ProblemInstanceController {
    @Autowired
    private ProblemInstanceService problemInstanceService;

    @GetMapping
    public ResponseEntity<List<ProblemInstance>> getProblemInstances(){
        List<ProblemInstance> instances = problemInstanceService.getAllProblemInstances();
        return new ResponseEntity<List<ProblemInstance>>(instances, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<ProblemInstance> createOrUpdatePlayer(@RequestBody ProblemInstance instance){
        ProblemInstance instanceCreated = problemInstanceService.createOrUpdateProblemInstance(instance);
        return new ResponseEntity<ProblemInstance>(instanceCreated, new HttpHeaders(), HttpStatus.CREATED);
    }
}
