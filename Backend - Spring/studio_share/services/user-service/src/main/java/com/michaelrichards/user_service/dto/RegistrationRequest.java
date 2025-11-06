package com.michaelrichards.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegistrationRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    private String avatarURI;

    @NotBlank
    private String password;

    @Past
    private LocalDate birthday;

    @NotBlank
    private Boolean isAccountPrivate;
}
