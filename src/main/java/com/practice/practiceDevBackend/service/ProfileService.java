package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.profile.ProfileResponse;
import com.practice.practiceDevBackend.dto.profile.ProfileUpdateRequest;
import com.practice.practiceDevBackend.entity.User;
import com.practice.practiceDevBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

     private final UserRepository userRepository;

    public ProfileResponse getProfile(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        ProfileResponse response = new ProfileResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }

    public ProfileResponse updateProfile(
            ProfileUpdateRequest request,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        user.setUsername(request.getUsername());

        userRepository.save(user);

        ProfileResponse response = new ProfileResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());

        return response;
    }
}
