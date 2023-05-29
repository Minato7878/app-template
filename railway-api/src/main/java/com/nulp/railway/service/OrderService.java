package com.nulp.railway.service;

import com.nulp.railway.entity.Order;
import com.nulp.railway.entity.Ticket;
import com.nulp.railway.exception.EntityNotFoundException;
import com.nulp.railway.repository.OrderRepository;
import com.nulp.railway.repository.RailwayRepository;
import com.nulp.railway.repository.SeatRepository;
import com.nulp.railway.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final RailwayRepository railwayRepository;
    private final UserService userService;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getAllUserOrders() {
        var user = userService.getCurrentUser();
        return orderRepository.findAllByUserId(user.getId());
    }

    public Order getById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
    }

    public List<Order> unbookOrder(Long railwayId) {
        var railway = railwayRepository.findById(railwayId)
                .orElseThrow(() -> new EntityNotFoundException("Railway with id " + railwayId + " not found"));
        var user = userService.getCurrentUser();
        var order = orderRepository.findByRailwayIdAndUserId(railwayId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("This user doesn't have order for railway " + railwayId));
        var ticket = ticketRepository.findByOrderId(order.getId());
        var seat = ticket.getSeat();
        seat.setAvailable(true);
        seat.setRailway(railway);
        seatRepository.save(seat);
        ticketRepository.delete(ticket);
        orderRepository.delete(order);
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order saveWithTicket(Order order, Ticket ticket) {
        var railway = railwayRepository.findById(order.getRailway().getId())
                .orElseThrow(() -> new EntityNotFoundException("Railway with id " + order.getRailway().getId() + " not found"));
        order.setRailway(railway);
        var savedOrder = orderRepository.save(order);
        var seat = seatRepository.findByCarriageAndSeatNumberAndRailwayId(ticket.getSeat().getCarriage(),
                ticket.getSeat().getSeatNumber(), railway.getId());
        seat.setAvailable(false);
        seatRepository.save(seat);
        ticket.setOrder(savedOrder);
        ticket.setSeat(seat);
        ticketRepository.save(ticket);
        return savedOrder;
    }

    public Order update(Order order, Long orderId) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}