package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.ReservationRequestDto;
import com.cyclone.dineflow.dto.responsedto.ReservationResponseDto;
import com.cyclone.dineflow.security.UserPrincipal;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ReservationService {
    ReservationResponseDto createReservation(UserPrincipal userPrincipal, ReservationRequestDto reservationRequestDto);

    List<ReservationResponseDto> viewMyReservations(UserPrincipal userPrincipal);

    ReservationResponseDto viewReservationById(String reservationId);

    String cancelReservation(String reservationId);

    List<ReservationResponseDto> viewAllReservationsByBranch(String branchId);

    List<ReservationResponseDto> viewAllReservationsByBranchForToday(String branchId);

    String confirmReservation(String reservationId);

    String completeReservation(String reservationId);
}
