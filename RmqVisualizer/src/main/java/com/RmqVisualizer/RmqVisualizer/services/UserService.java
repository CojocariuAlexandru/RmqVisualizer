package com.RmqVisualizer.RmqVisualizer.services;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import com.RmqVisualizer.RmqVisualizer.models.User;
import com.RmqVisualizer.RmqVisualizer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProblemInstanceService problemInstanceService;
    private User currentUser;

    public void setCurrentUser(User user){
        currentUser = user;
    }

    public User getCurrentUser(){
        return currentUser;
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
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User createOrUpdatePlayer(User user) {
        user.setId(UUID.randomUUID());
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
    }
}
