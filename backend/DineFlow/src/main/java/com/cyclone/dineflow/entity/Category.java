package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.CategoryActive;
import jakarta.persistence.*;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, columnDefinition = "CHAR(36)", name = "branchId")
    private Branch branch;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryActive isActive = CategoryActive.ACTIVE;

    @OneToMany(mappedBy = "category")
    private List<MenuItem> menuItems;
}
