package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.auth.LoginRequest;
import com.practice.practiceDevBackend.dto.auth.LoginResponse;
import com.practice.practiceDevBackend.dto.auth.RegisterRequest;
import com.practice.practiceDevBackend.dto.auth.RegisterResponse;
import com.practice.practiceDevBackend.entity.User;
import com.practice.practiceDevBackend.entity.enums.UserRole;
import com.practice.practiceDevBackend.exception.EmailAlreadyRegisteredException;
import com.practice.practiceDevBackend.exception.InvalidCredentialsException;
import com.practice.practiceDevBackend.mapper.UserMapper;
import com.practice.practiceDevBackend.repository.UserRepository;
import com.practice.practiceDevBackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request){



        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyRegisteredException("Email is already registered.");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);
        System.out.println("Creating was successfully.");
        return userMapper.toResponse(user);
    }

    public LoginResponse login(LoginRequest request){

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException(
                        "Invalid email or password."
                ));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponse(user.getUsername(), user.getEmail(), token, user.getRole());
    }
}
