package com.cyclone.dineflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 11-04-2026
 */
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId", nullable = false,  columnDefinition = "CHAR(36)")
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String city;

    @Column(nullable = false,  unique = true, length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;

    @OneToMany(mappedBy = "branch")
    private List<Category> categories;

    @OneToMany(mappedBy = "branch")
    private List<RestaurantTable> restaurantTables;

    @OneToMany(mappedBy = "branch")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "branch")
    private List<Orders> orders;
}
