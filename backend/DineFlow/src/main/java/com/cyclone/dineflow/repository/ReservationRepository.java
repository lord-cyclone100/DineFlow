package com.cyclone.dineflow.repository;

import com.cyclone.dineflow.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 19-04-2026
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    public List<Reservation> findAllByUserId(String userId);
    public List<Reservation> findAllByBranchId(String branchId);
}
