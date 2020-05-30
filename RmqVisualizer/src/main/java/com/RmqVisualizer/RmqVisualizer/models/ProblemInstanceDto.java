package com.RmqVisualizer.RmqVisualizer.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class ProblemInstanceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ElementCollection
    private List<Integer> numbers;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
