package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 17-04-2026
 */
@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String userId;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String tableId;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String branchId;

    @Column(nullable = false)
    private Integer guestCount;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private LocalTime reservationTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private String specialNotes;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
