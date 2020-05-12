package com.RmqVisualizer.RmqVisualizer.repositories;

import com.RmqVisualizer.RmqVisualizer.models.ProblemInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemInstanceRepository extends JpaRepository<ProblemInstance, UUID> {
}
