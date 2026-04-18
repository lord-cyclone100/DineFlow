package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.OrderItemStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String orderId;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String menuItemId;

    @Column(nullable = false,  columnDefinition = "CHAR(36)")
    private String variantId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer unitPrice;

    private String notes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderItemStatus status = OrderItemStatus.PENDING;
}
