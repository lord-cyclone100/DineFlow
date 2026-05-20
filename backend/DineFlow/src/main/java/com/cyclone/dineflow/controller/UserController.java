package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.ChangeRolesRequestDto;
import com.cyclone.dineflow.dto.responsedto.ChangeRolesResponseDto;
import com.cyclone.dineflow.dto.responsedto.UserResponseDto;
import com.cyclone.dineflow.entity.User;
import com.cyclone.dineflow.entity.data.UserStatus;
import com.cyclone.dineflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> fetchAllUsers(){
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/admin/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> fetchParticularUser(@PathVariable String id){
        return ResponseEntity.ok(userService.fetchParticularUser(id));
    }

    @PatchMapping("/admin/users/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUserStatus(@PathVariable String id, @RequestParam UserStatus userStatus){
        return ResponseEntity.ok(userService.updateUserStatus(id,userStatus));
    }

    @DeleteMapping("/admin/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PatchMapping("/admin/users/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChangeRolesResponseDto> changeUserRoles(@PathVariable String id, @RequestBody ChangeRolesRequestDto roleChangeArr){
        return ResponseEntity.ok(userService.changeUserRoles(id, roleChangeArr));
    }
}
