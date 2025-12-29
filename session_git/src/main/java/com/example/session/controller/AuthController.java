package com.example.session.controller;

import com.example.session.dto.UserRequestDTO;
import com.example.session.dto.UserResponseDTO;
import com.example.session.security.JwtUtil;
import com.example.session.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO dto) {
        UserResponseDTO user = userService.register(dto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO dto) {
        var userDetails = userService.loadUserByUsername(dto.getUsername());
        if (!userDetails.getPassword().equals(dto.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        var roles = userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        String token = jwtUtil.generateToken(userDetails.getUsername(), roles);

        return ResponseEntity.ok(token);
    }
}
