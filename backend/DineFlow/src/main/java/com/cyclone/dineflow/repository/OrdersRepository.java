package com.cyclone.dineflow.repository;

import com.cyclone.dineflow.entity.Orders;
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
public interface OrdersRepository extends JpaRepository<Orders, String> {
    public List<Orders> findAllByUserId(String userId);
    public List<Orders> findAllByBranchId(String branchId);
}
