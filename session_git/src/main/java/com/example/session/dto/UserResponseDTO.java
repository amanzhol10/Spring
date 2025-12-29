package com.example.session.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String username;
    private List<String> permissions;
    private boolean enabled;
}


