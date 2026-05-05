package com.cyclone.dineflow.dto.requestdto;

import java.time.LocalTime;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 05-05-2026
 */
public record BranchRequestDto(
        String name,
        String address,
        String city,
        String phoneNumber,
        LocalTime openTime,
        LocalTime closeTime
)
{}
