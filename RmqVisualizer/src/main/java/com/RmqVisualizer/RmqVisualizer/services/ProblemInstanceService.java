package com.RmqVisualizer.RmqVisualizer.services;

import com.RmqVisualizer.RmqVisualizer.SparseTableLogic.RmqSolver;
import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.models.ProblemInstanceDto;
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
    private RmqSolver rmqSolver;

    @Autowired
    public ProblemInstanceService(ProblemInstanceRepository problemInstanceRepository, RmqSolver rmqSolver){
        this.problemInstanceRepository = problemInstanceRepository;
        this.rmqSolver = new RmqSolver();
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
            System.out.println(instance.getId());
            System.out.println(id);
            System.out.println("");
            if(instance.getId().equals(id)){
                return instance;
            }
        }
        return null;
    }

    public ProblemInstance createOrUpdateProblemInstance(ProblemInstance instance){
        instance.setPrecalculationNumbers(rmqSolver.getPrecalculationNumbers(instance.getNumbers()));
        instance.setUser(new User());
        instance = problemInstanceRepository.save(instance);
        return instance;
    }

    public List<ProblemInstance> getProblemInstanceByUserId(UUID id){
        List<ProblemInstance> allInstances = problemInstanceRepository.findAll();
        for(ProblemInstance instance : allInstances){
            if(!instance.getUser().getId().equals(id)){
                allInstances.remove(instance);
            }
        }
        return allInstances;
    }


    public Integer getMinimumNumber(ProblemInstance problemInstance, int leftIndex, int rightIndex){
        int finalResult;
        finalResult = rmqSolver.getMinimumFromRange(problemInstance.getNumbers(), problemInstance.getPrecalculationNumbers(), leftIndex, rightIndex);
        return finalResult;
    }

    public void deleteProblemInstance(ProblemInstance problemInstance){
        problemInstanceRepository.delete(problemInstance);
    }



}
