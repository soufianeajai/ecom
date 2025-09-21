package com.codewithmosh.store.dtos.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDto {
    @NotBlank(message = "name is required")
    @Size(min = 2, max = 100, message = "name  must be between 2 and 100 characters")
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 3, message = "Size must be greater than 3")
    private String password;
}
