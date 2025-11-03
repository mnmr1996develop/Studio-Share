package com.michaelrichards.user_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegistrationRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday;
    private Boolean isAccountPrivate;
}
