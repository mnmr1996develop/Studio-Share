package com.michaelrichards.follow_service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserFollowerResponse {
    private Integer totalFollowerCount;
    private Integer totalFollowingCount;
    private List<UserDataResponse> users;
}
