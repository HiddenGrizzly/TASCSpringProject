package com.example.moviemingle.models.users;

import com.example.moviemingle.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    
    User fromRegisterDto(RegisterReq dto);
    
    UserRes toUserRes(User user);
    
}
