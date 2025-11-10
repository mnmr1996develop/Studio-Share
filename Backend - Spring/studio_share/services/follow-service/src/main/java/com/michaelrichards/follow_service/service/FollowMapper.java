package com.michaelrichards.follow_service.service;

import com.michaelrichards.follow_service.dto.FollowedUserResponse;
import com.michaelrichards.follow_service.dto.UserDataResponse;
import com.michaelrichards.follow_service.model.FollowerRelationship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowMapper {

    private final UserMapper userMapper;

    public FollowedUserResponse toFollowedUserResponse(FollowerRelationship relationship) {
        UserDataResponse followerDTO = userMapper.toUserDataResponse(relationship.getFollower());
        UserDataResponse followingDTO = userMapper.toUserDataResponse(relationship.getFollowing());

        return FollowedUserResponse.builder()
                .relationshipId(relationship.getFollowRelationId())
                .follower(followerDTO)
                .following(followingDTO)
                .timeFollowed(relationship.getTimeFollowed())
                .build();
    }
}
