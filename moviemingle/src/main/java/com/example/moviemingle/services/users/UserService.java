package com.example.moviemingle.services.users;

import com.example.moviemingle.entities.User;
import com.example.moviemingle.models.users.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    
    User register(RegisterReq dto);
    
    TokenRes login(LoginReq dto);
    
    User getById(Long id);
    
    User getByUsername(String username);
    
    void changePassword(User user, ChangePasswordReq dto);
    
    TokenRes refreshToken(RefreshTokenReq req);
    
    Page<User> getAll(Pageable pageable);
    
    User updateUser(User user, UserUpdateReq req);

    String changeAvatar(User user, MultipartFile avatar);
}
