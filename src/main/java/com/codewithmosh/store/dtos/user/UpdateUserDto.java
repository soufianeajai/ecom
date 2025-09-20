package com.codewithmosh.store.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    private String name;
    private  String email;
}
