package com.codewithmosh.store.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordDto {
    @NotBlank
    @Size(min = 3, message = "Size must be greater than 3")
    private String oldPassword;
    @NotBlank
    @Size(min = 3, message = "Size must be greater than 3")
    private String newPassword;
}
