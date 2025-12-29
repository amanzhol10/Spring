package com.example.session.mappers;

import com.example.session.dto.UserRequestDTO;
import com.example.session.dto.UserResponseDTO;
import com.example.session.entity.Permission;
import com.example.session.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "permissionsToNames")
    UserResponseDTO toDto(User user);

    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "namesToPermissions")
    User toEntity(UserRequestDTO dto);

    List<UserResponseDTO> toDtoList(List<User> users);

    @Named("permissionsToNames")
    default List<String> permissionsToNames(List<Permission> permissions) {
        if (permissions == null) return null;
        return permissions.stream().map(Permission::getName).collect(Collectors.toList());
    }

    @Named("namesToPermissions")
    default List<Permission> namesToPermissions(List<String> names) {
        if (names == null) return null;
        return names.stream().map(name -> {
            Permission p = new Permission();
            p.setName(name);
            return p;
        }).collect(Collectors.toList());
    }
}
