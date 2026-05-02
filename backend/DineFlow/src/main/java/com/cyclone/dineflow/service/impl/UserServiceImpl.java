package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.responsedto.UserResponseDto;
import com.cyclone.dineflow.dtomapper.ViewUserDtoMapper;
import com.cyclone.dineflow.entity.User;
import com.cyclone.dineflow.entity.data.UserStatus;
import com.cyclone.dineflow.repository.UserRepository;
import com.cyclone.dineflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public List<UserResponseDto> fetchAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map((user)-> ViewUserDtoMapper.toDto(user)).toList();
    }

    @Override
    public UserResponseDto fetchParticularUser(String id) {
        User foundUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found!"));
        return ViewUserDtoMapper.toDto(foundUser);
    }

    @Override
    public String updateUserStatus(String id, UserStatus userStatus) {
        User foundUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found!"));
        foundUser.setStatus(userStatus);
        userRepository.save(foundUser);
        return "Status updated";
    }

    @Override
    public String deleteUser(String id) {
        User foundUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found!"));
        userRepository.delete(foundUser);
        return "User Deleted";
    }
}
