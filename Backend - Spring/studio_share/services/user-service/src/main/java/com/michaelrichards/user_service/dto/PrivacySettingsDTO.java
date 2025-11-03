package com.michaelrichards.user_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrivacySettingsDTO {
    private boolean isAccountPrivate;
}
