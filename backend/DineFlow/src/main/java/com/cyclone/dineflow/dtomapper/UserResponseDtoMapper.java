package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.entity.User;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
public class UserResponseDtoMapper {
    public static RegisterResponseDto toDto(User user) {
        List<String> roles = user.getRoles().stream().map(role -> role.getRoleName().name()).toList();
        String provider = user.getProvider().name();
        return new RegisterResponseDto(
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                roles,
                provider,
                user.getStatus(),
                "User registered successfully"
        );
    }
}
