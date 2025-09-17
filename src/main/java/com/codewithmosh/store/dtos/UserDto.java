package com.codewithmosh.store.dtos;

import com.codewithmosh.store.entities.User;
import lombok.*;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;

    public static UserDto fromEntity(User user){
        return  new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
