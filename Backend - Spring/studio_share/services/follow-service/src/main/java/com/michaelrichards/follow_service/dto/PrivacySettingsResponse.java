package com.michaelrichards.follow_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrivacySettingsResponse {
    private boolean isAccountPrivate;
}