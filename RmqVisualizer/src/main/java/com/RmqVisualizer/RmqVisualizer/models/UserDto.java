package com.RmqVisualizer.RmqVisualizer.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @OneToMany
    List<ProblemInstance> problemInstanceList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProblemInstance> getProblemInstanceList() {
        return problemInstanceList;
    }

    public void setProblemInstanceList(List<ProblemInstance> problemInstanceList) {
        this.problemInstanceList = problemInstanceList;
    }
}
