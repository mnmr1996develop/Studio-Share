package com.michaelrichards.follow_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class FollowResponse {

    private UUID relationshipId;
    private UserDataResponse follower;
    private UserDataResponse following;
    private LocalDateTime timeFollowed;
}
