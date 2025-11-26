package com.reuben.simple_task_manager.dtos;

import com.reuben.simple_task_manager.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(Long id, @NotBlank String title, @NotBlank String description, TaskStatus status, @NotNull Long userId) {

}
