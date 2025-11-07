package com.reuben.simple_task_manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reuben.simple_task_manager.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByUserId(Long userId);
}
