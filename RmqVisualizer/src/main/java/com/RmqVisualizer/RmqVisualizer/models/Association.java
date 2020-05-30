package com.RmqVisualizer.RmqVisualizer.models;

import java.util.UUID;

public class Association {
    UUID userId;
    UUID problemId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProblemId() {
        return problemId;
    }

    public void setProblemId(UUID problemId) {
        this.problemId = problemId;
    }
}
