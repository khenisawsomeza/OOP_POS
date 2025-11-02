/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.core;

/**
 *
 * @author Marc Jacob
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandInvoker {
    private Stack<Command> executedCommands;
    private List<String> auditLog;
    
    public CommandInvoker() {
        this.executedCommands = new Stack<>();
        this.auditLog = new ArrayList<>();
    }
    
    public boolean executeCommand(Command command) {
        boolean success = command.execute();
        
        if (success) {
            executedCommands.push(command);
            logCommand(command);
        } else {
            System.out.println("Command execution failed");
        }
        
        return success;
    }
    
    public boolean undoLastCommand() {
        if (executedCommands.isEmpty()) {
            System.out.println("No commands to undo");
            return false;
        }
        
        Command command = executedCommands.pop();
        boolean success = command.undo();
        
        if (success) {
            auditLog.add("[UNDO] " + command.getLogEntry());
            System.out.println("Command undone successfully");
        } else {
            System.out.println("Undo failed");
            executedCommands.push(command); // Put it back if undo failed
        }
        
        return success;
    }
    
    private void logCommand(Command command) {
        String logEntry = command.getLogEntry();
        auditLog.add(logEntry);
        System.out.println("LOG: " + logEntry);
    }
    
    public List<String> getAuditLog() {
        return new ArrayList<>(auditLog);
    }
    
    public void printAuditLog() {
        System.out.println("\n========== AUDIT LOG ==========");
        if (auditLog.isEmpty()) {
            System.out.println("No commands executed yet");
        } else {
            for (int i = 0; i < auditLog.size(); i++) {
                System.out.println((i + 1) + ". " + auditLog.get(i));
            }
        }
        System.out.println("================================\n");
    }
    
    public int getCommandHistorySize() {
        return executedCommands.size();
    }
    
    public void clearHistory() {
        executedCommands.clear();
        auditLog.clear();
        System.out.println("Command history cleared");
    }
}