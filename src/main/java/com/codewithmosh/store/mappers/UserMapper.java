package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.RegisterUserDto;
import com.codewithmosh.store.dtos.UpdateUserDto;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "name", target = "username")
    UserDto todto(User user);
    User toEntity(RegisterUserDto registerUserDto);
    void updateUserFromDto(UpdateUserDto dto, @MappingTarget User user);

}
