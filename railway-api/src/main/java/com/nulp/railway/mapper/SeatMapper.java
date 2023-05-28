package com.nulp.railway.mapper;

import com.nulp.railway.dto.SeatDto;
import com.nulp.railway.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SeatMapper {

    @Mapping(target = "id", ignore = true)
    Seat toEntity(SeatDto seatDto);

    SeatDto toDto(Seat seat);

    List<SeatDto> toDtoList(List<Seat> seats);

    List<Seat> toEntityList(List<SeatDto> seatDtos);
}