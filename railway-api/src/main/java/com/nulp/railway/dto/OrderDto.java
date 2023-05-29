package com.nulp.railway.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long railwayId;
    private Long userId;
    private LocalDateTime orderDatetime;
    public String getOrderDatetime() {
        return orderDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}