package com.reuben.simple_task_manager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(Long id, @NotBlank String title, @NotBlank String description, @NotBlank String status, @NotNull Long userId) {

}
