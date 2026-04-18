package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.OrderStatus;
import com.cyclone.dineflow.entity.data.OrderType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String userId;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String branchId;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String tableId;

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
}
