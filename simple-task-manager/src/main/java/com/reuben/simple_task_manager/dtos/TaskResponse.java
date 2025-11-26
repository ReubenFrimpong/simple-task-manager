package com.reuben.simple_task_manager.dtos;

import com.reuben.simple_task_manager.enums.TaskStatus;

public record TaskResponse(Long id, String title, String description, Enum<TaskStatus> status) {

}
