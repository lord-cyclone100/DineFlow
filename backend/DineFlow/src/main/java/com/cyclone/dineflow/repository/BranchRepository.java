package com.cyclone.dineflow.repository;

import com.cyclone.dineflow.entity.Branch;
import com.cyclone.dineflow.entity.Restaurant;
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
public interface BranchRepository extends JpaRepository<Branch, String> {
    Optional<Branch> findByName(String name);
}
