package com.michaelrichards.user_service.service;

import com.michaelrichards.user_service.dto.PrivacySettingsDTO;
import com.michaelrichards.user_service.dto.RegistrationRequest;
import com.michaelrichards.user_service.dto.UserDataResponse;
import com.michaelrichards.user_service.model.PrivacySetting;
import com.michaelrichards.user_service.model.User;
import com.michaelrichards.user_service.repository.PrivacyRepository;
import com.michaelrichards.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final PrivacyRepository privacyRepository;

    public UserDataResponse registerUser(RegistrationRequest registrationRequest) {

        PrivacySetting privacySetting = PrivacySetting.builder()
                .isAccountPrivate(registrationRequest.getIsAccountPrivate())
                .build();

        if (userRepository.existsByEmailIgnoreCase(registrationRequest.getEmail()))
            throw new RuntimeException("Email already exist");

        if (userRepository.existsByUsernameIgnoreCase(registrationRequest.getUsername()))
            throw new RuntimeException("Username already exist");

        User user = User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .birthday(registrationRequest.getBirthday())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .privacySetting(privacySetting)
                .build();

        privacyRepository.save(privacySetting);
        User savedUser = userRepository.save(user);

        return mapUserToUserDataResponse(savedUser);
    }


    private UserDataResponse mapUserToUserDataResponse(User user) {
        PrivacySettingsDTO privacySetting = PrivacySettingsDTO.builder()
                .isAccountPrivate(user.getPrivacySetting().getIsAccountPrivate())
                .build();

        return UserDataResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .userPrivacySettings(privacySetting)
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public List<UserDataResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapUserToUserDataResponse).toList();
    }

    //Todo: replace generic runtime exception with something more informative
    public UserDataResponse findByUsername(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new RuntimeException(""));
        return mapUserToUserDataResponse(user);
    }
}
