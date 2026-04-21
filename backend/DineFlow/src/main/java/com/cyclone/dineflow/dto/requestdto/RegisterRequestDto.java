package com.cyclone.dineflow.dto.requestdto;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
public record RegisterRequestDto(
        String name,
        String email,
        String password,
        String phoneNumber
)
{}
