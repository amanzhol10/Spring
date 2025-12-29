package com.example.session.test;


import com.example.session.entity.User;
import com.example.session.repository.UserRepository;
import com.example.session.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService service;

    @Test
    void loadUserByUsername_success() {
        User user = new User();
        user.setUsername("user");

        when(userRepository.findByUsername("user"))
                .thenReturn(Optional.of(user));

        var result = service.loadUserByUsername("user");

        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    void loadUserByUsername_notFound() {
        when(userRepository.findByUsername("bad"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername("bad"));
    }
}

