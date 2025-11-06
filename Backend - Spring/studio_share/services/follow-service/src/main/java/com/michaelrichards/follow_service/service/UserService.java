package com.michaelrichards.follow_service.service;

import com.michaelrichards.follow_service.dto.FollowResponse;
import com.michaelrichards.follow_service.dto.UserDataResponse;
import com.michaelrichards.follow_service.model.User;
import com.michaelrichards.follow_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public Optional<User> getUserEntity(UUID id) {
        return userRepository.findById(id);
    }


    public User getUserByIdIfNotExistSaveNewUser(UserDataResponse userDataResponse) {

        return userRepository.findById(userDataResponse.getUserId()).orElseGet(() -> {
            User newSavedFollowUser = User.builder()
                    .userId(userDataResponse.getUserId())
                    .isAccountPrivate(userDataResponse.getUserPrivacySettings().isAccountPrivate())
                    .build();
            return userRepository.save(newSavedFollowUser);

        });
    }
}
