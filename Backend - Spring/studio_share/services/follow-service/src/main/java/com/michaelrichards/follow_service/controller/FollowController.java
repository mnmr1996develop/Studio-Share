package com.michaelrichards.follow_service.controller;

import com.michaelrichards.follow_service.dto.FollowedUserResponse;
import com.michaelrichards.follow_service.dto.UserDataResponse;
import com.michaelrichards.follow_service.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/followers/")
public class FollowController {


    private final FollowService followService;

    @PostMapping("follow")
    public ResponseEntity<FollowedUserResponse> followUser(@RequestParam("followerId") UUID followerId, @RequestParam("followingId") UUID followingId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followService.followUser(followerId, followingId));
    }

    @PostMapping("unfollow")
    ResponseEntity<Boolean> unfollowUser(@RequestParam("followerId") UUID followerId, @RequestParam("followingId") UUID followingId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followService.unfollowUser(followerId, followingId));
    }

    @GetMapping
    public ResponseEntity<List<UserDataResponse>> getFollowersPaged(@RequestParam("userId") UUID userId, int pageNumber){
        return ResponseEntity.ok().body(followService.getFollowers(userId, pageNumber));
    }



}
