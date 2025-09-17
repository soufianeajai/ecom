package com.codewithmosh.store.dtos;

import lombok.*;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
