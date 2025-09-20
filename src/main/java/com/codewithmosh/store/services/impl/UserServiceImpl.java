package com.codewithmosh.store.services.impl;

import com.codewithmosh.store.dtos.user.PasswordDto;
import com.codewithmosh.store.dtos.user.RegisterUserDto;
import com.codewithmosh.store.dtos.user.UpdateUserDto;
import com.codewithmosh.store.dtos.user.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.exceptions.UserNotFoundException;
import com.codewithmosh.store.exceptions.WrongPasswordException;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import com.codewithmosh.store.services.UserService;
import com.codewithmosh.store.utils.UserSortField;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserDto> getAllUsers(String sortBy){
        String validatedSortBy = UserSortField.fromString(sortBy).getFieldName();
        return userRepository.findAll(Sort.by(validatedSortBy)).stream()
                .map(userMapper::todto)
                .toList();
    }
    public UserDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("the user with id " + id + " not found"));
        return userMapper.todto(user);
    }
    public UserDto createUser(RegisterUserDto registerUserDto){
        User user = userMapper.toEntity(registerUserDto);
        return userMapper.todto(userRepository.save(user));
    }

    @Transactional
    public UserDto updateUser(UpdateUserDto updateUserDto, Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("the user with id " + id + " not found"));
        userMapper.updateUserFromDto(updateUserDto, user);
        return userMapper.todto(user);
    }
    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("the user with id " + id + " not found"));
        userRepository.delete(user);
    }

    @Transactional
    public UserDto changeUserPassword(Long id, PasswordDto passwordDto){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("the user with id " + id + " not found"));
        if (!Objects.equals(passwordDto.getOldPassword(), user.getPassword()))
            throw new WrongPasswordException("wrong old password");
        user.setPassword(passwordDto.getNewPassword());
        return userMapper.todto(user);
    }
}
