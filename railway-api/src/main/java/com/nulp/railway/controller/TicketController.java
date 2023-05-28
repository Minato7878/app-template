package com.nulp.railway.controller;

import com.nulp.railway.dto.SeatDto;
import com.nulp.railway.dto.TicketDto;
import com.nulp.railway.mapper.SeatMapper;
import com.nulp.railway.mapper.TicketMapper;
import com.nulp.railway.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_TICKET_ID = "Endpoint - {}({}) call";
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final SeatMapper seatMapper;

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAll() {
        log.debug(LOG_MESSAGE, "getAllTickets");
        var tickets = ticketMapper.toDtoList(ticketService.getAll());
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/seats")
    public ResponseEntity<List<SeatDto>> getAvailableSeats() {
        log.debug(LOG_MESSAGE, "getAvailableSeats");
        var seats = seatMapper.toDtoList(ticketService.getAvailableSeats());
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDto> getById(@PathVariable Long ticketId) {
        log.debug(LOG_MESSAGE_WITH_TICKET_ID, "getTicketById", ticketId);
        var ticket = ticketMapper.toDto(ticketService.getById(ticketId));
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TicketDto> create(@RequestBody @Valid TicketDto ticketDto) {
        log.debug(LOG_MESSAGE, "createTicket");
        var ticket = ticketService.save(ticketMapper.toEntity(ticketDto));
        return new ResponseEntity<>(ticketMapper.toDto(ticket), HttpStatus.CREATED);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketDto> update(@PathVariable Long ticketId, @RequestBody @Valid TicketDto ticketDto) {
        log.debug(LOG_MESSAGE_WITH_TICKET_ID, "updateTicket", ticketId);
        var ticket = ticketService.update(ticketMapper.toEntity(ticketDto), ticketId);
        return new ResponseEntity<>(ticketMapper.toDto(ticket), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{ticketId}")
    public void deleteById(@PathVariable Long ticketId) {
        log.debug(LOG_MESSAGE_WITH_TICKET_ID, "deleteTicketById", ticketId);
        ticketService.deleteById(ticketId);
    }
}
