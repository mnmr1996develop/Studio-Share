package com.michaelrichards.user_service.controller;

import com.michaelrichards.user_service.dto.RegistrationRequest;
import com.michaelrichards.user_service.dto.UserDataResponse;
import com.michaelrichards.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users/")
public class UserController {

    private final UserService userService;


    @GetMapping("{userId}")
    public ResponseEntity<UserDataResponse> findByUsername(@PathVariable("userId") UUID userId){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDataResponse>> getListOfUsersByUserId(@RequestParam("userIds") List<UUID> uuids){
        return ResponseEntity.ok().body(userService.findByUserIdIn(uuids));
    }

    @PostMapping
    public ResponseEntity<UserDataResponse> registerUser(@RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registrationRequest));
    }
}
