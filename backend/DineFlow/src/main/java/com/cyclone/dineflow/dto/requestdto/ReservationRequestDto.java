package com.cyclone.dineflow.dto.requestdto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequestDto(
        String tableId,
        String branchId,
        Integer guestCount,
        LocalDate reservationDate,
        LocalTime reservationTime,
        String specialNotes
) {}
