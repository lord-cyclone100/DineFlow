package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.ReservationRequestDto;
import com.cyclone.dineflow.dto.responsedto.ReservationResponseDto;
import com.cyclone.dineflow.security.UserPrincipal;
import com.cyclone.dineflow.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> createReservation(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody ReservationRequestDto reservationRequestDto){
        return ResponseEntity.ok(reservationService.createReservation(userPrincipal,reservationRequestDto));
    }

    @GetMapping("/reservations/my")
    public ResponseEntity<List<ReservationResponseDto>> viewMyReservations(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(reservationService.viewMyReservations(userPrincipal));
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationResponseDto> viewReservationById(@PathVariable String reservationId){
        return ResponseEntity.ok(reservationService.viewReservationById(reservationId));
    }

    @PatchMapping("/reservations/{reservationId}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable String reservationId){
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @GetMapping("/branches/{branchId}/reservations")
    public ResponseEntity<List<ReservationResponseDto>> viewAllReservationByBranch(@PathVariable String branchId){
        return ResponseEntity.ok(reservationService.viewAllReservationsByBranch(branchId));
    }

    @GetMapping("/branches/{branchId}/reservations/today")
    public ResponseEntity<List<ReservationResponseDto>> viewAllReservationByBranchForToday(@PathVariable String branchId){
        return ResponseEntity.ok(reservationService.viewAllReservationsByBranchForToday(branchId));
    }

    @PatchMapping("/reservations/{reservationId}/confirm")
    public ResponseEntity<String> confirmReservation(@PathVariable String reservationId){
        return ResponseEntity.ok(reservationService.confirmReservation(reservationId));
    }

    @PatchMapping("/reservations/{reservationId}/complete")
    public ResponseEntity<String> completeReservation(@PathVariable String reservationId){
        return ResponseEntity.ok(reservationService.completeReservation(reservationId));
    }
}
