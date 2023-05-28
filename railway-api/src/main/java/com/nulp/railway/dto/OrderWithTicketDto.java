package com.nulp.railway.dto;

import com.nulp.railway.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithTicketDto {
    private OrderDto order;
    private TicketDto ticket;
    private String railwayNumber;
}
