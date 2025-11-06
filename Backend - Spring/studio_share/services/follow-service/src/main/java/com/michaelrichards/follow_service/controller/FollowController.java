package com.michaelrichards.follow_service.controller;

import com.michaelrichards.follow_service.dto.FollowResponse;
import com.michaelrichards.follow_service.service.FollowService;
import jakarta.ws.rs.GET;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/follow")
public class FollowController {


    private final FollowService followService;

    @PostMapping
    public ResponseEntity<FollowResponse> followUser(@RequestParam("followerId") UUID followerId, @RequestParam("followingId") UUID followingId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(followService.followUser(followerId, followingId));
    }

}
