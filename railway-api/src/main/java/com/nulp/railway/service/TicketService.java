package com.nulp.railway.service;

import com.nulp.railway.entity.Seat;
import com.nulp.railway.entity.Ticket;
import com.nulp.railway.exception.EntityNotFoundException;
import com.nulp.railway.repository.SeatRepository;
import com.nulp.railway.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Seat> getAvailableSeats() {
        return seatRepository.findAllByIsAvailableIsTrue();
    }

    public List<Ticket> getByOrderIds(List<Long> orderIds) {
        return ticketRepository.findAllByOrderIds(orderIds);
    }

    public Ticket getById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with ID: " + ticketId));
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket update(Ticket ticket, Long ticketId) {
        ticket.setId(ticketId);
        return ticketRepository.save(ticket);
    }

    public void deleteById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}