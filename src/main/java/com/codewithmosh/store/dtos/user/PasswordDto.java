package com.codewithmosh.store.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordDto {
    private String oldPassword;
    private String newPassword;
}
