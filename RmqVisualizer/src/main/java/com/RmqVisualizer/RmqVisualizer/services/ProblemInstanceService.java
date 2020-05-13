package com.RmqVisualizer.RmqVisualizer.services;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.repositories.ProblemInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProblemInstanceService {
    @Autowired
    private ProblemInstanceRepository problemInstanceRepository;
    @Autowired
    private UserService userService;

    public List<ProblemInstance> getAllProblemInstances(){
        List<ProblemInstance> allInstances = problemInstanceRepository.findAll();
        if(allInstances.size() > 0){
            return allInstances;
        }
        else{
            return new ArrayList<>();
        }
    }

    public List<ProblemInstance> getProblemInstancesByCurrentUser(){
        List<ProblemInstance> allInstances = problemInstanceRepository.findAll();
        for(ProblemInstance instance : allInstances){
            if(instance.getUser().equals(userService.getCurrentUser())){
                allInstances.add(instance);
            }
        }
        if(allInstances.size() > 0){
            return allInstances;
        }
        else{
            return new ArrayList<>();
        }
    }

    public ProblemInstance getProblemInstanceById(UUID id){
        List<ProblemInstance> allInstances = problemInstanceRepository.findAll();
        for(ProblemInstance instance : allInstances){
            if(instance.getId().equals(id)){
                return instance;
            }
        }
        return null;
    }

    public ProblemInstance createOrUpdateProblemInstance(ProblemInstance problemInstance){
        problemInstance.setId(UUID.randomUUID());
        problemInstance = problemInstanceRepository.save(problemInstance);
        userService.assignUserToProblemInstance(userService.getCurrentUser().getId(), problemInstance.getId());
        this.assignProblemInstanceToUser(userService.getCurrentUser().getId(), problemInstance.getId());
        return problemInstance;
    }

    public void deleteProblemInstance(ProblemInstance problemInstance){
        problemInstanceRepository.delete(problemInstance);
    }

    public void assignProblemInstanceToUser(UUID idUser, UUID idInstance){
        List<ProblemInstance> instances = problemInstanceRepository.findAll();
        for(ProblemInstance instance : instances){
            if(instance.getId().equals(idInstance)){
                instance.setUser(userService.getUserById(idUser));
            }
        }
    }
}
