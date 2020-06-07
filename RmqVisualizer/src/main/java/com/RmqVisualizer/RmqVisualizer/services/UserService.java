package com.RmqVisualizer.RmqVisualizer.services;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.repositories.ProblemInstanceRepository;
import com.RmqVisualizer.RmqVisualizer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    private ProblemInstanceRepository problemInstanceRepository;
    private ProblemInstanceService problemInstanceService;

    @Autowired
    public UserService(UserRepository userRepository, ProblemInstanceService problemInstanceService, ProblemInstanceRepository problemInstanceRepository){
        this.userRepository = userRepository;
        this.problemInstanceService = problemInstanceService;
        this.problemInstanceRepository = problemInstanceRepository;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.size() > 0) {
            return allUsers;
        } else {
            return new ArrayList<>();
        }
    }

    public User getUserById(UUID id){
        List<User> allUsers = userRepository.findAll();
        for(User user : allUsers){
            System.out.println(user.getId());
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public ProblemInstance getInstanceByUserAndInstanceIndex(int userIndex, int instanceIndex){
        int i = 0;
        List<ProblemInstance> allInstances = problemInstanceRepository.findAll();
        for(ProblemInstance instance : allInstances){
            if(instance.getUser().equals(this.getUserByIndex(userIndex))){
                i = i +1;
            }
            if(i == instanceIndex){
                return instance;
            }
        }
        return null;
    }

    public User getUserByIndex(int index){
        int i;
        List<User> allUsers = userRepository.findAll();
        i = 0;
        for(User user: allUsers){
            i = i + 1;
            if(i == index)
                return user;
        }
        return null;
    }

    public User getUserByName(String userName){
        List<User> allUsers = userRepository.findAll();
        for(User user : allUsers){
            if(user.getName().compareTo(userName) == 0){
                return user;
            }
        }
        return null;
    }

    public User createOrUpdateUser(String userName) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(userName);
        user.setProblemInstanceList(null);
        user = userRepository.save(user);
        return user;
    }

    public void deletePlayer(User player) {
        userRepository.delete(player);
    }

    public void assignUserToProblemInstance(UUID idUser, UUID idProblemInstance){
        User user = this.getUserById(idUser);
        ProblemInstance instance = problemInstanceService.getProblemInstanceById(idProblemInstance);
        user.getProblemInstanceList().add(instance);
        instance.setUser(user);

        userRepository.save(user);
        problemInstanceRepository.save(instance);
    }
}
