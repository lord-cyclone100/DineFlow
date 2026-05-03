package com.cyclone.dineflow.entity;

import com.cyclone.dineflow.entity.data.UserRoles;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 10-04-2026
 */
@Entity
@Data
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,  unique = true)
    private UserRoles roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<User> userId;

    public Roles (UserRoles roleName) {
        this.roleName = roleName;
    }
}
