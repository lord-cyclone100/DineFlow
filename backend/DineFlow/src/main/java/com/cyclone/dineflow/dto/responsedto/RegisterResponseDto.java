package com.cyclone.dineflow.dto.responsedto;

import com.cyclone.dineflow.entity.data.UserStatus;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
public record RegisterResponseDto(
        String name,
        String email,
        String phoneNumber,
        List<String> role,
        String provider,
        UserStatus status,
        String message
)
{}
