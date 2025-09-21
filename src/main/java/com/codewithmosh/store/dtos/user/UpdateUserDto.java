package com.codewithmosh.store.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    @NotBlank(message = "name is required")
    @Size(min = 2, max = 100, message = "name  must be between 2 and 100 characters")
    private String name;
    @NotBlank
    @Email
    private String email;
}
