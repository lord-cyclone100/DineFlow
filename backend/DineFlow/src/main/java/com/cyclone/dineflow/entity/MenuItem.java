package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.MenuAvailability;
import com.cyclone.dineflow.entity.data.MenuCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, columnDefinition = "CHAR(36)", name = "categoryId")
    private Category category;

    @Column(nullable = false)
    private String name;

    private String description;

    private Integer basePrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MenuAvailability menuAvailability =  MenuAvailability.AVAILABLE;

    @Column(nullable = false)
    private Integer preparationTimeInMinutes;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "menuItem")
    private List<MenuItemVariant> menuItemVariants;

    @OneToMany(mappedBy = "menuItem")
    private List<AddOn> addOns;

    @OneToMany(mappedBy = "menuItem")
    private List<OrderItem> orderItems;
}
