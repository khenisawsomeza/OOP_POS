/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.core;

/**
 *
 * @author Marc Jacob
 */
import java.time.LocalDateTime;

public interface Command {
    boolean execute();
    boolean undo();
    String getLogEntry();
    LocalDateTime getTimestamp();
    String getExecutedBy();
    boolean isExecuted();
}
