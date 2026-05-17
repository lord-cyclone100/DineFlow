package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.ReservationResponseDto;
import com.cyclone.dineflow.entity.Reservation;

public class ReservationResponseDtoMapper {
    public static ReservationResponseDto toDto(Reservation reservation, String message){
        return new ReservationResponseDto(
                reservation.getUser().getName(),
                reservation.getBranch().getRestaurant().getName(),
                reservation.getBranch().getName(),
                reservation.getTable().getTableNumber(),
                reservation.getGuestCount(),
                reservation.getReservationDate(),
                reservation.getReservationTime(),
                reservation.getSpecialNotes(),
                reservation.getStatus(),
                message
        );
    }
}
