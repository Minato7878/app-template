package com.nulp.railway.repository;

import com.nulp.railway.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Additional query methods can be added here if needed

    Optional<Order> findByRailwayIdAndUserId(Long railwayId, Long userId);

    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByRailwayId(Long railwayId);
}