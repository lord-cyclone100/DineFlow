package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.LoginRequestDto;
import com.cyclone.dineflow.dto.requestdto.RegisterRequestDto;
import com.cyclone.dineflow.dto.responsedto.LoginResponseDto;
import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("/auth/refresh")
//    public ResponseEntity<LoginResponseDto> refreshUser(@RequestBody LoginRequestDto userRequestDto) {
//
//    }

    @GetMapping("/get")
    public String printHello(){
        return "Hello World";
    }
}