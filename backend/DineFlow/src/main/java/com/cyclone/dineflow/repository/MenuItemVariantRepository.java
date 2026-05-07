package com.cyclone.dineflow.repository;

import com.cyclone.dineflow.entity.MenuItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 19-04-2026
 */
@Repository
public interface MenuItemVariantRepository extends JpaRepository<MenuItemVariant, String> {
    Optional<MenuItemVariant> findByName(String name);
}
