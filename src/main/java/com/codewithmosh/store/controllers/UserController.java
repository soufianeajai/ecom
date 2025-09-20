package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.PasswordDto;
import com.codewithmosh.store.dtos.RegisterUserDto;
import com.codewithmosh.store.dtos.UpdateUserDto;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.utils.UserSortField;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.codewithmosh.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false, defaultValue = "", name = "sort") String sortBy) {
        String validatedSortBy = UserSortField.fromString(sortBy).getFieldName();
        var users = userRepository.findAll(Sort.by(validatedSortBy)).stream()
                .map(userMapper::todto)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(userMapper::todto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody RegisterUserDto registerUserDto, UriComponentsBuilder uriBuilder){
        User user = userMapper.toEntity(registerUserDto);
        UserDto userDto = userMapper.todto(userRepository.save(user));
        var path = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(path).body(userDto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        userMapper.updateUserFromDto(updateUserDto, user);
        return  ResponseEntity.ok(userMapper.todto(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    @Transactional
    public ResponseEntity<UserDto> changeUserPassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto, UriComponentsBuilder uri){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        if (!Objects.equals(passwordDto.getOldPassword(), user.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        user.setPassword(passwordDto.getNewPassword());
        var path = uri.path("/{id}/change-password").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(path).body(userMapper.todto(user));
    }
}
