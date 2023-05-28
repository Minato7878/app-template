package com.nulp.railway.mapper;

import com.nulp.railway.dto.RailwayDto;
import com.nulp.railway.entity.Railway;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RailwayMapper {

    RailwayDto toDto(Railway railway);

    @Mapping(target = "id", ignore = true)
    Railway toEntity(RailwayDto railwayDto);

    List<RailwayDto> toDtoList(List<Railway> railways);

    List<Railway> toEntityList(List<RailwayDto> railwayDtos);
}