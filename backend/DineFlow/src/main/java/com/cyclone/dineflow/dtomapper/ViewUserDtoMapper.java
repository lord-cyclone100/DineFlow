package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.dto.responsedto.UserResponseDto;
import com.cyclone.dineflow.entity.User;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 02-05-2026
 */
public class ViewUserDtoMapper {
    public static UserResponseDto toDto(User user) {
        List<String> roles = user.getRoles().stream().map(role -> role.getRoleName().name()).toList();
        String provider = user.getProvider().name();
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                roles,
                provider,
                user.getStatus()
        );
    }
}
