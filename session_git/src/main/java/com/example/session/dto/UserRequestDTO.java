package com.example.session.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDTO {

    private String username;
    private String password;
    private List<String> permissions;
}
