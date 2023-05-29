package com.nulp.railway.mapper;

import com.nulp.railway.dto.RailwayDto;
import com.nulp.railway.entity.Railway;
import com.nulp.railway.entity.Seat;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RailwayMapper {

    RailwayDto toDto(Railway railway);

    @Mapping(target = "id", ignore = true)
    Railway toEntity(RailwayDto railwayDto);

    List<RailwayDto> toDtoList(List<Railway> railways);

    List<Railway> toEntityList(List<RailwayDto> railwayDtos);

    @AfterMapping
    default void mapAvailableSeats(Railway railway, @MappingTarget RailwayDto railwayDto) {
        if (railway.getSeats() != null) {
            long availableSeats = railway.getSeats().stream()
                    .filter(Seat::isAvailable)
                    .count();
            railwayDto.setAvailableSeats(availableSeats);
        }
    }
}