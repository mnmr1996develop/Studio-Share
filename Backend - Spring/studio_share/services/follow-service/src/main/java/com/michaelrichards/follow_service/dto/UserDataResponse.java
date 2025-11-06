package com.michaelrichards.follow_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserDataResponse {

    private UUID userId;
    private String username;
    private String firstName;
    private PrivacySettingsResponse userPrivacySettings;
    private String lastName;
    private String avatarURI;
    private LocalDateTime createdAt;
    private Integer age;
}
