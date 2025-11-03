package com.michaelrichards.user_service.controller;

import com.michaelrichards.user_service.dto.RegistrationRequest;
import com.michaelrichards.user_service.dto.UserDataResponse;
import com.michaelrichards.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDataResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDataResponse> findByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserDataResponse> registerUser(@RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registrationRequest));
    }
}
