package com.cyclone.dineflow.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto(
        int status,
        String message,
        LocalDateTime timestamp
)
{}
