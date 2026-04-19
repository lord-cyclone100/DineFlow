package com.cyclone.dineflow.repository;

import com.cyclone.dineflow.entity.AddOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 19-04-2026
 */
@Repository
public interface AddOnRepository extends JpaRepository<AddOn, String> {
}
