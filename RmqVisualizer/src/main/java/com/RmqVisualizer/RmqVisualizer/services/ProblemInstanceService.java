package com.RmqVisualizer.RmqVisualizer.services;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.repositories.ProblemInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProblemInstanceService {
    private ProblemInstanceRepository problemInstanceRepository;
    private UserService userService;

    @Autowired
    public ProblemInstanceService(ProblemInstanceRepository problemInstanceRepository, UserService userService){
        this.problemInstanceRepository = problemInstanceRepository;
        this.userService = userService;
    }

    public List<ProblemInstance> getAllProblemInstances(){
        List<ProblemInstance> allInstances = problemInstanceRepository.findAll();
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
        return problemInstance;
    }

    public void createPrecalculationNumbers(ProblemInstance instance){
        instance.getPrecalculationNumbers().clear();

    }

    public void deleteProblemInstance(ProblemInstance problemInstance){
        problemInstanceRepository.delete(problemInstance);
    }
//
//    public int getMinimumNumber(int startIndex, int finalIndex){
//
//    }


}
