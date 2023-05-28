package com.nulp.railway.mapper;

import com.nulp.railway.dto.OrderDto;
import com.nulp.railway.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderMapper {

    @Mapping(target = "railwayId", source = "order.railway.id")
    @Mapping(target = "userId", source = "order.user.id")
    OrderDto toDto(Order order);

    @Mapping(target = "railway.id", source = "orderDto.railwayId")
    @Mapping(target = "user.id", source = "orderDto.userId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDatetime", ignore = true)
    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);

    List<Order> toEntityList(List<OrderDto> orderDtos);
}