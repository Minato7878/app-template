package com.nulp.railway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RailwayDto {
    private Long id;
    private String railwayNumber;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureDatetime;
    private LocalDateTime arrivalDatetime;
}