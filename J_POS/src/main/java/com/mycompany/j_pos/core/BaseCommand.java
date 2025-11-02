package com.mycompany.j_pos.core;

import java.time.LocalDateTime;

public abstract class BaseCommand implements Command {
    protected String executedBy;
    protected LocalDateTime timestamp;
    protected boolean executed;
    
    public BaseCommand(String executedBy) {
        this.executedBy = executedBy;
        this.timestamp = LocalDateTime.now();
        this.executed = false;
    }
    
    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String getExecutedBy() {
        return executedBy;
    }
    
    @Override
    public boolean isExecuted() {
        return executed;
    }
}