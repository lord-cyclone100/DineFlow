package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.ReservationRequestDto;
import com.cyclone.dineflow.dto.responsedto.ReservationResponseDto;
import com.cyclone.dineflow.dtomapper.ReservationResponseDtoMapper;
import com.cyclone.dineflow.entity.*;
import com.cyclone.dineflow.entity.data.ReservationStatus;
import com.cyclone.dineflow.repository.*;
import com.cyclone.dineflow.security.UserPrincipal;
import com.cyclone.dineflow.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final BranchRepository branchRepository;

    @Override
    public ReservationResponseDto createReservation(UserPrincipal userPrincipal, ReservationRequestDto reservationRequestDto) {
        String userId = userPrincipal.userId();
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User does not exist"));
        Branch foundBranch = branchRepository.findById(reservationRequestDto.branchId()).orElseThrow(()-> new RuntimeException("Branch does not exist"));
        RestaurantTable foundRestaurantTable = restaurantTableRepository.findById(reservationRequestDto.tableId()).orElseThrow(()-> new RuntimeException("Table does not exist"));

        LocalDate reservationDate = reservationRequestDto.reservationDate();
        LocalTime reservationTime = reservationRequestDto.reservationTime();

        Reservation newReservation = Reservation.builder()
                .user(foundUser)
                .branch(foundBranch)
                .table(foundRestaurantTable)
                .guestCount(reservationRequestDto.guestCount())
                .reservationDate(reservationDate)
                .reservationTime(reservationTime)
                .status(ReservationStatus.PENDING)
                .specialNotes(reservationRequestDto.specialNotes())
                .build();
        reservationRepository.save(newReservation);
        return ReservationResponseDtoMapper.toDto(newReservation,"Reservation done");
    }

    @Override
    public List<ReservationResponseDto> viewMyReservations(UserPrincipal userPrincipal) {
        String userId = userPrincipal.userId();
        List<ReservationResponseDto> myReservations = reservationRepository.findAllByUserId(userId).stream().map((reservation)->ReservationResponseDtoMapper.toDto(reservation,null)).toList();
        return myReservations;
    }

    @Override
    public ReservationResponseDto viewReservationById(String reservationId) {
        Reservation foundReservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        return ReservationResponseDtoMapper.toDto(foundReservation,null);
    }

    @Override
    public String cancelReservation(String reservationId) {
        Reservation foundReservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        foundReservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(foundReservation);
        return "Reservation cancelled";
    }

    @Override
    public List<ReservationResponseDto> viewAllReservationsByBranch(String branchId) {
        List<ReservationResponseDto> branchReservations = reservationRepository.findAllByBranchId(branchId).stream().map((reservation)->ReservationResponseDtoMapper.toDto(reservation,null)).toList();
        return branchReservations;
    }

    @Override
    public List<ReservationResponseDto> viewAllReservationsByBranchForToday(String branchId) {
        List<ReservationResponseDto> todaysReservations = reservationRepository.findAll().stream().filter((reservation)-> reservation.getReservationDate().equals(LocalDate.now())).map((reservation)->ReservationResponseDtoMapper.toDto(reservation,null)).toList();
        return todaysReservations;
    }

    @Override
    public String confirmReservation(String reservationId) {
        Reservation foundReservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        foundReservation.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(foundReservation);
        return "Reservation confirmed";
    }

    @Override
    public String completeReservation(String reservationId) {
        Reservation foundReservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        foundReservation.setStatus(ReservationStatus.COMPLETED);
        reservationRepository.save(foundReservation);
        return "Reservation completed";
    }
}
