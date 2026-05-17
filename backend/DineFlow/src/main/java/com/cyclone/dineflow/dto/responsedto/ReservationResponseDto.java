package com.cyclone.dineflow.dto.responsedto;

import com.cyclone.dineflow.entity.data.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReservationResponseDto(
        String customerName,
        String restaurantName,
        String branchName,
        String tableNumber,
        Integer guestCount,
        LocalDate reservationDate,
        LocalTime reservationTime,
        String specialNotes,
        ReservationStatus reservationStatus,
        String message
) {}
