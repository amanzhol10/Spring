package com.example.session.test;

import com.example.session.dto.UserRequestDTO;
import com.example.session.dto.UserResponseDTO;
import com.example.session.entity.Permission;
import com.example.session.entity.User;
import com.example.session.mappers.UserMapper;
import com.example.session.repository.PermissionRepository;
import com.example.session.repository.UserRepository;
import com.example.session.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PermissionRepository permissionRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    void register_shouldAssignRoleUser() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUsername("newUser");
        dto.setPassword("user123");

        User userEntity = new User();
        userEntity.setUsername("newUser");
        userEntity.setPassword("user123");

        Permission roleUser = new Permission();
        roleUser.setName("ROLE_USER");

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUsername("newUser");
        responseDTO.setPermissions(List.of("ROLE_USER"));

        when(userMapper.toEntity(dto)).thenReturn(userEntity);
        when(permissionRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roleUser));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toDto(userEntity)).thenReturn(responseDTO);

        UserResponseDTO result = userService.register(dto);

        assertThat(result.getUsername()).isEqualTo("newUser");
        assertThat(result.getPermissions()).containsExactly("ROLE_USER");

        verify(userMapper).toEntity(dto);
        verify(permissionRepository).findByName("ROLE_USER");
        verify(userRepository).save(userEntity);
        verify(userMapper).toDto(userEntity);
    }

    @Test
    void getById_shouldReturnUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user1");

        UserResponseDTO dto = new UserResponseDTO();
        dto.setUsername("user1");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(dto);

        UserResponseDTO result = userService.getById(1L);

        assertThat(result.getUsername()).isEqualTo("user1");
        verify(userRepository).findById(1L);
        verify(userMapper).toDto(user);
    }

    @Test
    void deleteUser_shouldCallRepository() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }
}
