package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.user.PasswordDto;
import com.codewithmosh.store.dtos.user.RegisterUserDto;
import com.codewithmosh.store.dtos.user.UpdateUserDto;
import com.codewithmosh.store.dtos.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers(String sortBy);
    UserDto getUserById(Long id);
    UserDto createUser(RegisterUserDto registerUserDto);
    UserDto updateUser(UpdateUserDto updateUserDto, Long id);
    void deleteUser(Long id);
    UserDto changeUserPassword(Long id, PasswordDto passwordDto);
}
