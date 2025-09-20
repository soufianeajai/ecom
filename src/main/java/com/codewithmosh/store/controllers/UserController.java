package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.user.PasswordDto;
import com.codewithmosh.store.dtos.user.RegisterUserDto;
import com.codewithmosh.store.dtos.user.UpdateUserDto;
import com.codewithmosh.store.dtos.user.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.services.UserService;
import com.codewithmosh.store.utils.UserSortField;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false, defaultValue = "", name = "sort") String sortBy) {
        return ResponseEntity.ok(userService.getAllUsers(sortBy));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody RegisterUserDto registerUserDto, UriComponentsBuilder uriBuilder){
        UserDto userDto = userService.createUser(registerUserDto);
        var path = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(path).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable Long id){
        return  ResponseEntity.ok(userService.updateUser(updateUserDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<UserDto> changeUserPassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto, UriComponentsBuilder uri){
        UserDto userDto = userService.changeUserPassword(id, passwordDto);
        var path = uri.path("/{id}/change-password").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(path).body(userDto);
    }
}
