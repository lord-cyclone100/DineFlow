package com.cyclone.dineflow.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 05-05-2026
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BranchResponseDto(
        String name,
        String city,
        String address,
        String message
)
{}
