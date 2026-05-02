package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.ChangePasswordRequestDto;
import com.cyclone.dineflow.dto.requestdto.LoginRequestDto;
import com.cyclone.dineflow.dto.requestdto.RegisterRequestDto;
import com.cyclone.dineflow.dto.responsedto.LoginResponseDto;
import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.security.UserPrincipal;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
public interface AuthService {
    RegisterResponseDto registerUser(RegisterRequestDto userRequestDto);

    LoginResponseDto loginUser(LoginRequestDto userRequestDto);

    LoginResponseDto refreshUser(UserPrincipal principal);

    RegisterResponseDto getCurrentUser(UserPrincipal principal);

    RegisterResponseDto updateCurrentUserDetails(UserPrincipal principal, RegisterRequestDto userRequestDto);

    String changePassword(UserPrincipal principal, ChangePasswordRequestDto password);

    String logoutUser(UserPrincipal principal);
}
