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

    public List<ProblemInstance> getAllProblemInstances(){
        List<ProblemInstance> allInstances = problemInstanceRepository.findAll();
        if(allInstances.size() > 0){
            return allInstances;
        }
        else{
            return new ArrayList<>();
        }
    }

    public ProblemInstance createOrUpdateProblemInstance(ProblemInstance problemInstance){
        problemInstance.setId(UUID.randomUUID());
        problemInstance = problemInstanceRepository.save(problemInstance);
        return problemInstance;
    }

    public void deleteProblemInstance(ProblemInstance problemInstance){
        problemInstanceRepository.delete(problemInstance);
    }
}
