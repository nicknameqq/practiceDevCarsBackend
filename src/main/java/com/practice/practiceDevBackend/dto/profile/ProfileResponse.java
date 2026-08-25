package com.practice.practiceDevBackend.dto.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
}
