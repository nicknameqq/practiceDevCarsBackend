package com.practice.practiceDevBackend.controller;


import com.practice.practiceDevBackend.dto.profile.ProfileResponse;
import com.practice.practiceDevBackend.dto.profile.ProfileUpdateRequest;
import com.practice.practiceDevBackend.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                profileService.getProfile(authentication)
        );
    }

    @PutMapping
    public ResponseEntity<ProfileResponse> updateProfile(
        @Valid @RequestBody ProfileUpdateRequest profileUpdateRequest, Authentication authentication){
        return ResponseEntity.ok(profileService.updateProfile(profileUpdateRequest, authentication));
    }

}
