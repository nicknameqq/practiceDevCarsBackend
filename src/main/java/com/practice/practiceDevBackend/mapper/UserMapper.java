package com.practice.practiceDevBackend.mapper;

import com.practice.practiceDevBackend.dto.auth.RegisterRequest;
import com.practice.practiceDevBackend.dto.auth.RegisterResponse;
import com.practice.practiceDevBackend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return user;
    }

    public RegisterResponse toResponse(User user){
        return new RegisterResponse(
                user.getUsername(),
                user.getEmail()
        );
    }
}
