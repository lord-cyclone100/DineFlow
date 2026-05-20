package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.ChangePasswordRequestDto;
import com.cyclone.dineflow.dto.requestdto.LoginRequestDto;
import com.cyclone.dineflow.dto.requestdto.RegisterRequestDto;
import com.cyclone.dineflow.dto.responsedto.LoginResponseDto;
import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.security.UserPrincipal;
import com.cyclone.dineflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 20-04-2026
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody RegisterRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.registerUser(userRequestDto));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.loginUser(userRequestDto));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<LoginResponseDto> refreshUser(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(authService.refreshUser(principal));
    }

    @GetMapping("/auth/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RegisterResponseDto> getCurrrentUser(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(authService.getCurrentUser(principal));
    }

    @PutMapping("/auth/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RegisterResponseDto> updateCurrrentUserDetails(@AuthenticationPrincipal UserPrincipal principal, @RequestBody RegisterRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.updateCurrentUserDetails(principal, userRequestDto));
    }

    @PatchMapping("/auth/changepassword")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal UserPrincipal principal, @RequestBody ChangePasswordRequestDto password){
        return ResponseEntity.ok(authService.changePassword(principal, password));
    }

    @PostMapping("/auth/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> logoutUser(@AuthenticationPrincipal UserPrincipal principal){
        return ResponseEntity.ok(authService.logoutUser(principal));
    }
}