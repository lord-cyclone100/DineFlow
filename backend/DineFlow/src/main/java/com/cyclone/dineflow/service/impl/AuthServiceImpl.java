package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.ChangePasswordRequestDto;
import com.cyclone.dineflow.dto.requestdto.LoginRequestDto;
import com.cyclone.dineflow.dto.requestdto.RegisterRequestDto;
import com.cyclone.dineflow.dto.responsedto.LoginResponseDto;
import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.dtomapper.UserResponseDtoMapper;
import com.cyclone.dineflow.entity.RefreshToken;
import com.cyclone.dineflow.entity.Roles;
import com.cyclone.dineflow.entity.User;
import com.cyclone.dineflow.entity.data.UserRoles;
import com.cyclone.dineflow.exceptions.custom.InvalidPasswordException;
import com.cyclone.dineflow.exceptions.custom.UserAlreadyExistsException;
import com.cyclone.dineflow.exceptions.custom.UserNotFoundException;
import com.cyclone.dineflow.repository.RefreshTokenRepository;
import com.cyclone.dineflow.repository.RolesRepository;
import com.cyclone.dineflow.repository.UserRepository;
import com.cyclone.dineflow.security.JwtUtil;
import com.cyclone.dineflow.security.TokenType;
import com.cyclone.dineflow.security.UserPrincipal;
import com.cyclone.dineflow.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDto registerUser(RegisterRequestDto userRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDto.email());

        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException(existingUser.get().getEmail());
        }

        Roles roles = rolesRepository.findByRoleName(UserRoles.CUSTOMER).orElseThrow(()->new RuntimeException("Role not found"));

        User user = User.builder()
                .name(userRequestDto.name())
                .email(userRequestDto.email())
                .password(passwordEncoder.encode(userRequestDto.password()))
                .phoneNumber(userRequestDto.phoneNumber())
                .roles(List.of(roles))
                .build();
        userRepository.save(user);
        log.info("User {} saved", user.getId());
        return UserResponseDtoMapper.toDto(user);
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto userRequestDto) {
        User foundUser = userRepository.findByEmail(userRequestDto.email()).orElseThrow(() -> new UserNotFoundException("User not found with email " + userRequestDto.email()));
        if(!passwordEncoder.matches(userRequestDto.password(), foundUser.getPassword())){
            throw new InvalidPasswordException("Invalid Password");
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

    @Override
    public LoginResponseDto refreshUser(UserPrincipal principal) {
        String userId = principal.userId();
        List<String> userRoles = principal.roles();
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        String accessToken = jwtUtil.buildAccessToken(foundUser.getId(),userRoles, TokenType.ACCESS);
        String refreshToken = jwtUtil.buildRefreshToken(foundUser.getId(),userRoles, TokenType.REFRESH);

        String type = "Bearer";

        RefreshToken refreshTokenRecord =  RefreshToken.builder()
                .token(refreshToken)
                .user(foundUser)
                .build();

        refreshTokenRepository.save(refreshTokenRecord);

        return new LoginResponseDto(accessToken, refreshToken, type);
    }

    @Override
    public RegisterResponseDto getCurrentUser(UserPrincipal principal) {
        String userId = principal.userId();

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        return UserResponseDtoMapper.toDto(foundUser);
    }

    @Override
    public RegisterResponseDto updateCurrentUserDetails(UserPrincipal principal, RegisterRequestDto userRequestDto) {
        String userId = principal.userId();

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        foundUser.setName(userRequestDto.name());
        foundUser.setEmail(userRequestDto.email());
        foundUser.setPhoneNumber(userRequestDto.phoneNumber());

        userRepository.save(foundUser);

        return UserResponseDtoMapper.toDto(foundUser);
    }

    @Override
    public String changePassword(UserPrincipal principal, ChangePasswordRequestDto password) {
        String userId = principal.userId();

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        foundUser.setPassword(passwordEncoder.encode(password.password()));
        userRepository.save(foundUser);
        return "Password changed successfully";
    }

    @Override
    @Transactional
    public String logoutUser(UserPrincipal principal) {
        String userId = principal.userId();

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        refreshTokenRepository.deleteByUser(foundUser);
        return "Refresh Tokens deleted successfully";
    }
}
