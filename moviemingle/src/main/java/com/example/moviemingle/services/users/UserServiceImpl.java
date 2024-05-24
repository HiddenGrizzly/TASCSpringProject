package com.example.moviemingle.services.users;

import com.example.moviemingle.entities.Role;
import com.example.moviemingle.entities.RoleType;
import com.example.moviemingle.entities.User;
import com.example.moviemingle.exceptions.NotfoundException;
import com.example.moviemingle.exceptions.PasswordNotMatchException;
import com.example.moviemingle.exceptions.UnauthorizedRequestException;
import com.example.moviemingle.models.users.*;
import com.example.moviemingle.repositories.RoleRepository;
import com.example.moviemingle.repositories.UserRepository;
import com.example.moviemingle.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public User register(RegisterReq dto) {
        Role role = roleRepo.findByRoleName(RoleType.USER).orElseThrow(() -> new NotfoundException("Role not found"));
        User user = Mappers.getMapper(UserMapper.class).fromRegisterDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        return userRepo.save(user);
    }
    
    @Override
    public TokenRes login(LoginReq dto) {
        User currentUser = getByUsername(dto.getUsername());
        if(!passwordEncoder.matches(CharBuffer.wrap(dto.getPassword()), currentUser.getPassword())) throw new PasswordNotMatchException();
        return TokenRes.builder()
                .userId(currentUser.getId())
                .username(currentUser.getUsername())
                .roles(currentUser.getRoles().stream().map(r -> r.getRoleName().name()).collect(Collectors.toSet()))
                .accessToken(jwtUtil.createAccessToken(currentUser))
                .refreshToken(jwtUtil.createRefreshToken(currentUser))
                .build();
    }
    
    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NotfoundException("User not found"));
    }
    
    @Override
    public User getByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new NotfoundException("User not found"));
    }
    
    @Override
    public void changePassword(User user, ChangePasswordReq dto) {
        if(!passwordEncoder.matches(CharBuffer.wrap(dto.getOldPassword()), user.getPassword())){
            throw new PasswordNotMatchException();
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepo.save(user);
    }
    
    @Override
    public TokenRes refreshToken(HttpServletRequest req, HttpServletResponse res) {
        String authHeader = req.getHeader("Authorization");
        
        if (authHeader != null) {
            String[] headerElements = authHeader.split(" ");
            if (headerElements.length == 2 && "Bearer".equals(headerElements[0])) {
                String refreshToken = headerElements[1];
                String username = jwtUtil.extractUsername(refreshToken);
                User currentUser = getByUsername(username);
                return TokenRes.builder()
                        .userId(currentUser.getId())
                        .username(currentUser.getUsername())
                        .roles(currentUser.getRoles().stream().map(r -> r.getRoleName().name()).collect(Collectors.toSet()))
                        .accessToken(jwtUtil.createAccessToken(currentUser))
                        .refreshToken(refreshToken)
                        .build();
                
            }
        }
        throw new UnauthorizedRequestException();
    }
    
    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }
}
