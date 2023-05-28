package com.nulp.railway.mapper;

import com.nulp.railway.dto.SeatDto;
import com.nulp.railway.dto.TicketDto;
import com.nulp.railway.entity.Seat;
import com.nulp.railway.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = SeatMapper.class)
public interface TicketMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(target = "seatDto", source = "seat")
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(target = "seat", source = "seatDto")
    Ticket toEntity(TicketDto ticketDto);

    List<TicketDto> toDtoList(List<Ticket> tickets);

    List<Ticket> toEntityList(List<TicketDto> ticketDtos);

}
