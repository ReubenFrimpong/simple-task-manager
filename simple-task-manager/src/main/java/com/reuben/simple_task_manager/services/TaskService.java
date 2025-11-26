package com.reuben.simple_task_manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reuben.simple_task_manager.dtos.TaskRequest;
import com.reuben.simple_task_manager.dtos.TaskResponse;
import com.reuben.simple_task_manager.models.Task;
import com.reuben.simple_task_manager.models.User;
import com.reuben.simple_task_manager.repositories.TaskRepository;
import com.reuben.simple_task_manager.repositories.UserRepository;

@Service
public class TaskService {

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  UserRepository userRepository;

  public List<TaskResponse> getTasksByUserId(Long userId) {
    List<Task> tasks = taskRepository.findByUserId(userId);
    return tasks.stream()
        .map(task -> new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus()))
        .toList();
  } 

  public TaskResponse createTask(TaskRequest taskRequest) {
    Task task = new Task();
    task.setTitle(taskRequest.title());
    task.setDescription(taskRequest.description());
    task.setStatus(taskRequest.status());

    User user = userRepository.findById(taskRequest.userId()).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + taskRequest.userId()));
    task.setUser(user); 
    var savedTask = taskRepository.save(task);
    return new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(), savedTask.getStatus());
  }

  public TaskResponse updateTask(TaskRequest taskRequest) {
    Task task = taskRepository.findById(taskRequest.id()).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskRequest.id()));
    task.setTitle(taskRequest.title());
    task.setDescription(taskRequest.description());
    task.setStatus(taskRequest.status());
    var updatedTask =  taskRepository.save(task);
    return new TaskResponse(updatedTask.getId(), updatedTask.getTitle(), updatedTask.getDescription(), updatedTask.getStatus());
  }

  public void deleteTask(Long taskId) {
    if(taskId == null) {
      throw new IllegalArgumentException("Task ID cannot be null");
    }
    taskRepository.deleteById(taskId);
  }
}
