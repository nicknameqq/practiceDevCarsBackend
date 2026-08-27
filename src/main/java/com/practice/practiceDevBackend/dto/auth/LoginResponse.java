package com.practice.practiceDevBackend.dto.auth;

import com.practice.practiceDevBackend.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private String email;
    private String token;
    private UserRole role;
}
