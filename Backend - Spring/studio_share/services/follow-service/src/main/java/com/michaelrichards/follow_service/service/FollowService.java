package com.michaelrichards.follow_service.service;

import com.michaelrichards.follow_service.dto.FollowedUserResponse;
import com.michaelrichards.follow_service.dto.UserDataResponse;
import com.michaelrichards.follow_service.model.FollowerRelationship;
import com.michaelrichards.follow_service.model.User;
import com.michaelrichards.follow_service.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserMapper userMapper;

    private final FollowMapper followMapper;

    private final UserService userService;

    private final static String USERNAME = "username";

    private final static String TIME_FOLLOWED = "timeFollowed";


    @Transactional
    public FollowedUserResponse followUser(UUID followerId, UUID followingId) {

        if (followerId.equals(followingId)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        UserDataResponse followerResponse = userMapper.toUserDataResponse(followerId);

        UserDataResponse followingResponse = userMapper.toUserDataResponse(followerId);


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
        return followMapper.toFollowedUserResponse(savedRelationship);
    }

    @Transactional
    public Boolean unfollowUser(UUID followerId, UUID followingId) {
        User follower = userService.getUserEntity(followerId)
                .orElseThrow(() -> new RuntimeException("Follower user not found"));

        User following = userService.getUserEntity(followingId)
                .orElseThrow(() -> new RuntimeException("Following user not found"));

        followRepository.deleteByFollowerAndFollowing(follower, following);

        return true;
    }

    public List<UserDataResponse> getFollowers(UUID userId, int pageNumber) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(TIME_FOLLOWED)
                        .ascending()
                        .and(Sort.by(USERNAME)
                                .ascending()
                        )
        );

        List<FollowerRelationship> followerRelationships = followRepository.findFollowersByUserId(userId, pageable);



        List<UUID > uuidsList =followerRelationships.stream().map(
                followerRelationship ->
                        followerRelationship.getFollower().getUserId()
        ).toList();


        return userMapper.toListOfDataResponse(uuidsList);
    }








}
