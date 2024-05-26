package com.example.moviemingle.models.users;

import com.example.moviemingle.entities.Role;
import com.example.moviemingle.entities.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    
    User fromRegisterDto(RegisterReq dto);
    
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleSetToStringSet")
    UserRes toUserRes(User user);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(UserUpdateReq req, @MappingTarget User user);
    
    @Named("roleSetToStringSet")
    default Set<String> roleSetToStringSet(Set<Role> roles) {
        if (roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toSet());
    }
    
}
