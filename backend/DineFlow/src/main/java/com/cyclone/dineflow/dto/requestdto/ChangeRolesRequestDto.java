package com.cyclone.dineflow.dto.requestdto;

import com.cyclone.dineflow.entity.Roles;
import com.cyclone.dineflow.entity.data.UserRoles;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 03-05-2026
 */
public record ChangeRolesRequestDto(
        List<UserRoles> roles
)
{}
