package com.michaelrichards.follow_service.service;

import com.michaelrichards.follow_service.dto.FollowResponse;
import com.michaelrichards.follow_service.dto.UserDataResponse;
import com.michaelrichards.follow_service.model.FollowerRelationship;
import com.michaelrichards.follow_service.model.User;
import com.michaelrichards.follow_service.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final WebClient webClient;

    private final UserService userService;

    private final String USER_SERVICE_BASE_URL = "http://localhost:8081/api/v1/users/";


    @Transactional
    public FollowResponse followUser(UUID followerId, UUID followingId) {

        if (followerId.equals(followingId)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        UserDataResponse followerResponse = webClient.get()
                .uri(USER_SERVICE_BASE_URL + followerId)
                .retrieve()
                .bodyToMono(UserDataResponse.class)
                .block();



        UserDataResponse followingResponse = webClient.get()
                .uri(USER_SERVICE_BASE_URL + followingId)
                .retrieve()
                .bodyToMono(UserDataResponse.class)
                .block();


        if (followingResponse == null || followerResponse == null) {
            throw new RuntimeException("invalid");
        }


        log.info(followerResponse.toString());
        log.info(followingResponse.toString());

        if (followRepository.existsByFollower_UserIdAndFollowing_UserId(followerResponse.getUserId(), followingResponse.getUserId())) {
            throw new RuntimeException("Already following this user");
        }


        User following = userService.getUserByIdIfNotExistSaveNewUser(followerResponse);
        User follower = userService.getUserByIdIfNotExistSaveNewUser(followingResponse);

        FollowerRelationship relationship = FollowerRelationship.builder()
                .follower(following)
                .following(follower)
                .timeFollowed(LocalDateTime.now())
                .build();

        FollowerRelationship savedRelationship = followRepository.save(relationship);
        return convertToResponse(savedRelationship);
    }

    @Transactional
    public void unfollowUser(UUID followerId, UUID followingId) {
        User follower = userService.getUserEntity(followerId)
                .orElseThrow(() -> new RuntimeException("Follower user not found"));

        User following = userService.getUserEntity(followingId)
                .orElseThrow(() -> new RuntimeException("Following user not found"));

        followRepository.deleteByFollowerAndFollowing(follower, following);
    }

    public List<UserDataResponse> getFollowers(UUID userId, int pageNumber) {
        return null;
    }

    private FollowResponse convertToResponse(FollowerRelationship relationship) {
        UserDataResponse followerDTO = convertUserToDTO(relationship.getFollower());
        UserDataResponse followingDTO = convertUserToDTO(relationship.getFollowing());

        return FollowResponse.builder()
                .relationshipId(relationship.getFollowRelationId())
                .follower(followerDTO)
                .following(followingDTO)
                .timeFollowed(relationship.getTimeFollowed())
                .build();
    }

    private UserDataResponse convertUserToDTO(User user) {
        UserDataResponse followingResponse = webClient.get()
                .uri(USER_SERVICE_BASE_URL + user.getUserId())
                .retrieve()
                .bodyToMono(UserDataResponse.class)
                .block();
        if (followingResponse == null) throw new RuntimeException("Invalid");

        return followingResponse;
    }

}
