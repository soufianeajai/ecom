package com.codewithmosh.store.dtos.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDto {
    @NotBlank
    @Size(max = 255)
    private String name;
    @Email
    private String email;
    @Size(min = 3, message = "Size must be greater than 3")
    private String password;
}
