package com.michaelrichards.user_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserDataResponse{

    private UUID userId;
    private String username;
    private PrivacySettingsDTO userPrivacySettings;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private Integer age;

}
