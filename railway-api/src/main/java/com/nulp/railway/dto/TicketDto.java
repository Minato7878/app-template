package com.nulp.railway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    private Long orderId;
    private SeatDto seatDto;
    private String passengerFirstName;
    private String passengerLastName;
}