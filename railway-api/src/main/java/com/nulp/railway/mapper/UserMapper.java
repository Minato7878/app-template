package com.nulp.railway.mapper;

import com.nulp.railway.dto.UserDto;
import com.nulp.railway.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDTO);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> userDtos);
}