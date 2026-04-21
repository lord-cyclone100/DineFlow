package com.cyclone.dineflow.dto.responsedto;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 21-04-2026
 */
public record LoginResponseDto(
        String accessToken,
        String refreshToken,
        String type
)
{}
