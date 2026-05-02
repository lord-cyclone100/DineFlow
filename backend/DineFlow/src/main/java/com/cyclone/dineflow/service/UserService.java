package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.responsedto.UserResponseDto;
import com.cyclone.dineflow.entity.data.UserStatus;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
public interface UserService {
    List<UserResponseDto> fetchAllUsers();

    UserResponseDto fetchParticularUser(String id);

    String updateUserStatus(String id, UserStatus userStatus);

    String deleteUser(String id);
}
