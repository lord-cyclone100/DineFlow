package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.LoginRequestDto;
import com.cyclone.dineflow.dto.requestdto.RegisterRequestDto;
import com.cyclone.dineflow.dto.responsedto.LoginResponseDto;
import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.dtomapper.UserResponseDtoMapper;
import com.cyclone.dineflow.entity.RefreshToken;
import com.cyclone.dineflow.entity.Roles;
import com.cyclone.dineflow.entity.User;
import com.cyclone.dineflow.entity.data.UserRoles;
import com.cyclone.dineflow.repository.RefreshTokenRepository;
import com.cyclone.dineflow.repository.RolesRepository;
import com.cyclone.dineflow.repository.UserRepository;
import com.cyclone.dineflow.security.JwtUtil;
import com.cyclone.dineflow.security.TokenType;
import com.cyclone.dineflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RolesRepository rolesRepository;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterResponseDto registerUser(RegisterRequestDto userRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDto.email());

        if(existingUser.isPresent()){
            throw new RuntimeException("User with email already exists");
        }

        Roles roles = rolesRepository.findByRoleName(UserRoles.CUSTOMER).orElseThrow(()->new RuntimeException("Role not found"));

        User user =  User.builder()
                .name(userRequestDto.name())
                .email(userRequestDto.email())
                .password(userRequestDto.password())
                .phoneNumber(userRequestDto.phoneNumber())
                .roles(List.of(roles))
                .build();
        userRepository.save(user);
        log.info("User {} saved", user.getId());
        return UserResponseDtoMapper.toDto(user);
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto userRequestDto) {
        User foundUser = userRepository.findByEmail(userRequestDto.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(!foundUser.getPassword().equals(userRequestDto.password())){
            throw new RuntimeException("Passwords don't match");
        }

        List<String> userRoles = foundUser.getRoles().stream().map(role -> role.getRoleName().name()).toList();

        String accessToken = jwtUtil.buildAccessToken(foundUser.getId(),userRoles, TokenType.ACCESS);
        String refreshToken = jwtUtil.buildRefreshToken(foundUser.getId(),userRoles, TokenType.REFRESH);

//        String accessToken = "abcd1234";
//        String refreshToken = UUID.randomUUID().toString();
        String type = "Bearer";

        RefreshToken refreshTokenRecord =  RefreshToken.builder()
                .token(refreshToken)
                .user(foundUser)
                .build();

        refreshTokenRepository.save(refreshTokenRecord);

        return new LoginResponseDto(accessToken, refreshToken, type);
    }
}
