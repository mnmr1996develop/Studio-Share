package com.michaelrichards.follow_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicFollowResponse {

    private UUID userId;

    private Integer totalFollowers;

    private Integer totalUserFollowing;


}
