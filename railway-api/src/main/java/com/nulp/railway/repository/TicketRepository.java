package com.nulp.railway.repository;

import com.nulp.railway.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Additional query methods can be added here if needed
    Ticket findByOrderId(Long orderId);

    @Query("SELECT t FROM Ticket t WHERE t.order.id IN :orderIds")
    List<Ticket> findAllByOrderIds(@Param("orderIds") List<Long> orderIds);
}