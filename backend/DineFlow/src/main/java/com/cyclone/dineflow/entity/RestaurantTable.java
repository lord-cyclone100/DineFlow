package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.TableLocation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,  columnDefinition = "CHAR(36)", name = "branchId")
    private Branch branch;

    @Column(nullable = false)
    private String tableNumber;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TableLocation location;

    private boolean isActive = true;

    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "table")
    private List<Orders> orders;
}
