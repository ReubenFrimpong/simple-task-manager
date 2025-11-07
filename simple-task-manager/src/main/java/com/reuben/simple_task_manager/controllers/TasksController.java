package com.reuben.simple_task_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reuben.simple_task_manager.dtos.TaskRequest;
import com.reuben.simple_task_manager.dtos.TaskResponse;
import com.reuben.simple_task_manager.services.TaskService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/tasks")

public class TasksController {
  @Autowired
  private TaskService taskService;

  @GetMapping
  public ResponseEntity<?> getTasksByUserId(@RequestParam Long userId) {
    return ResponseEntity.ok(taskService.getTasksByUserId(userId)) ;
  }

  @PostMapping
  public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
      return ResponseEntity.ok(taskService.createTask(taskRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {      
      return ResponseEntity.ok(taskService.updateTask(taskRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable Long id) {
      taskService.deleteTask(id);
      return ResponseEntity.noContent().build();    
  }
  
}
