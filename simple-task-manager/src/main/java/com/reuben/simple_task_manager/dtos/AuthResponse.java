package com.reuben.simple_task_manager.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(String token, @JsonProperty("user") UserResponse userResponse) {}
