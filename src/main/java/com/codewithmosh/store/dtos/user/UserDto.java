package com.codewithmosh.store.dtos.user;

import lombok.*;

@Data
@AllArgsConstructor
public class UserDto {
//    @JsonProperty("user_id")  for renaming when sending a json response
    private Long id;
    private String username;
    private String email;
}
