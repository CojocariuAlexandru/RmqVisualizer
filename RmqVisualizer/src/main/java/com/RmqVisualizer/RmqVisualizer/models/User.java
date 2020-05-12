package com.RmqVisualizer.RmqVisualizer.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int name;
    @OneToMany
    List<ProblemInstance> problemInstanceList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public List<ProblemInstance> getProblemInstanceList() {
        return problemInstanceList;
    }

    public void setProblemInstanceList(List<ProblemInstance> problemInstanceList) {
        this.problemInstanceList = problemInstanceList;
    }
}
