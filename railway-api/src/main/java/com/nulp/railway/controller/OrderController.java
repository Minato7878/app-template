package com.nulp.railway.controller;

import com.nulp.railway.dto.OrderDto;
import com.nulp.railway.dto.OrderWithTicketDto;
import com.nulp.railway.dto.TicketDto;
import com.nulp.railway.entity.Order;
import com.nulp.railway.exception.BadRequestException;
import com.nulp.railway.exception.EntityNotFoundException;
import com.nulp.railway.mapper.OrderMapper;
import com.nulp.railway.mapper.TicketMapper;
import com.nulp.railway.service.OrderService;
import com.nulp.railway.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_ORDER_ID = "Endpoint - {}({}) call";
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final TicketMapper ticketMapper;
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        log.debug(LOG_MESSAGE, "getAllOrders");
        var orders = orderMapper.toDtoList(orderService.getAll());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<OrderWithTicketDto>> getOrdersWithTickets() {
        log.debug(LOG_MESSAGE, "getOrdersWithTickets");
        var orders = orderService.getAllUserOrders();
        var orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());
        List<TicketDto> tickets = ticketMapper.toDtoList(ticketService.getByOrderIds(orderIds));

        List<OrderWithTicketDto> ordersWithTickets = orders.stream()
                .map(order -> {
                    TicketDto ticketDto = tickets.stream()
                            .filter(ticket -> ticket.getOrderId().equals(order.getId()))
                            .findFirst()
                            .orElseThrow(() -> new EntityNotFoundException("Ticket not found for order: " + order.getId()));
                    return new OrderWithTicketDto(orderMapper.toDto(order), ticketDto, order.getRailway().getRailwayNumber());
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(ordersWithTickets, HttpStatus.OK);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long orderId) {
        log.debug(LOG_MESSAGE_WITH_ORDER_ID, "getOrderById", orderId);
        var order = orderMapper.toDto(orderService.getById(orderId));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/unbook/{railwayId}")
    public ResponseEntity<List<OrderDto>> unbookOrder(@PathVariable Long railwayId) {
        log.debug(LOG_MESSAGE_WITH_ORDER_ID, "unbookOrderById", railwayId);
        var orders = orderMapper.toDtoList(orderService.unbookOrder(railwayId));
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto orderDto) {
        log.debug(LOG_MESSAGE, "createOrder");
        var order = orderService.save(orderMapper.toEntity(orderDto));
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.CREATED);
    }

    @PostMapping("/ticket")
    public ResponseEntity<OrderDto> createWithTicket(@RequestBody @Valid OrderWithTicketDto orderWithTicketDto) {
        log.debug(LOG_MESSAGE, "createOrderWithTicket");
        var order = orderWithTicketDto.getOrder();
        if (Objects.isNull(order) || Objects.isNull(order.getRailwayId()))
            throw new BadRequestException("Cannot create OrderWithTicket because order is empty or corrupted!");

        var ticket = orderWithTicketDto.getTicket();

        if (Objects.isNull(ticket) || Strings.isBlank(ticket.getPassengerFirstName())
                || Strings.isBlank(ticket.getPassengerLastName()))
            throw new BadRequestException("Cannot create OrderWithTicket because ticket is empty or corrupted!");

        if (Objects.isNull(ticket.getSeatDto()) || Objects.isNull(ticket.getSeatDto().getSeatNumber())
                || Objects.isNull(ticket.getSeatDto().getCarriage()))
            throw new BadRequestException("Cannot create OrderWithTicket because seat in ticket is empty or corrupted!");

        var resultOrder = orderService.saveWithTicket(orderMapper.toEntity(order), ticketMapper.toEntity(ticket));
        return new ResponseEntity<>(orderMapper.toDto(resultOrder), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> update(@PathVariable Long orderId, @RequestBody @Valid OrderDto orderDto) {
        log.debug(LOG_MESSAGE_WITH_ORDER_ID, "updateOrder", orderId);
        var order = orderService.update(orderMapper.toEntity(orderDto), orderId);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{orderId}")
    public void deleteById(@PathVariable Long orderId) {
        log.debug(LOG_MESSAGE_WITH_ORDER_ID, "deleteOrderById", orderId);
        orderService.deleteById(orderId);
    }
}
