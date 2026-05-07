package com.cyclone.dineflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@Builder
public class MenuItemVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, columnDefinition = "CHAR(36)", name = "menuItemId")
    private MenuItem menuItem;

    @Column(nullable = false)
    private String name;

    private Integer extraPrice = 0;

    @OneToMany(mappedBy = "variant")
    private List<OrderItem> orderItemVariants;
}
