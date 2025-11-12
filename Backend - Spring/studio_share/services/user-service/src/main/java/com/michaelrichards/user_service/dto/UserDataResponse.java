package com.michaelrichards.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse{

    private UUID userId;
    private String username;
    private PrivacySettingsResponse userPrivacySettings;
    private String firstName;
    private String lastName;
    private String avatarURI;
    private LocalDateTime createdAt;
    private Integer age;

}
