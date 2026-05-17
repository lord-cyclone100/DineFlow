package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.OrderStatus;
import com.cyclone.dineflow.entity.data.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 18-04-2026
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,  columnDefinition = "CHAR(36)", name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,  columnDefinition = "CHAR(36)", name = "branchId")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,  columnDefinition = "CHAR(36)", name = "tableId")
    private RestaurantTable table;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PLACED;

    @Column(nullable = false)
    private Integer totalAmount;

    private Integer discount = 0;

    private Integer tax = 0;

    @Column(nullable = false)
    private Integer finalAmount;

    private String specialInstructions;

    private String deliveryAddress;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    private Payment payment;
}
