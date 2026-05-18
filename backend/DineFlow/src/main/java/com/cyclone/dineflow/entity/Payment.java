package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.PaymentMethod;
import com.cyclone.dineflow.entity.data.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, columnDefinition = "CHAR(36)", name = "orderId")
    private Orders order;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private String transactionId;

    private String razorpayOrderId;

    @Column(updatable = false)
    private LocalTime paidAt;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
