package com.example.session.service;

import com.example.session.dto.UserRequestDTO;
import com.example.session.dto.UserResponseDTO;
import com.example.session.entity.Permission;
import com.example.session.entity.User;
import com.example.session.mappers.UserMapper;
import com.example.session.repository.PermissionRepository;
import com.example.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final PermissionRepository permissionRepository;

    public UserResponseDTO register(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);

        user.setPassword(dto.getPassword());

        Permission userPermission = permissionRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Permission not found: ROLE_USER"));
        user.setPermissions(List.of(userPermission));

        return userMapper.toDto(userRepository.save(user));
    }



    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getPermissions() != null) {
            List<Permission> permissions = dto.getPermissions().stream()
                    .map(name -> permissionRepository.findByName(name)
                            .orElseThrow(() -> new RuntimeException("Permission not found: " + name)))
                    .collect(Collectors.toList());
            user.setPermissions(permissions);
        }

        return userMapper.toDto(userRepository.save(user));
    }

    public UserResponseDTO changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        return userMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
