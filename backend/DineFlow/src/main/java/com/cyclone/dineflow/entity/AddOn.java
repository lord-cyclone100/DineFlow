package com.cyclone.dineflow.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class AddOn {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "CHAR(36)", nullable = false)
    private String menuItemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer extraPrice = 0;
}
