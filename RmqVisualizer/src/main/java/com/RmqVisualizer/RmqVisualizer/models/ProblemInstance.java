package com.RmqVisualizer.RmqVisualizer.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class ProblemInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ElementCollection
    private List<Integer> numbers;
    @ElementCollection
    private List<Integer> precalculationNumbers;
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;
}
