package com.michaelrichards.follow_service.service;

import com.michaelrichards.follow_service.dto.UserDataResponse;
import com.michaelrichards.follow_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final WebClient webClient;

    public final static String USER_SERVICE_BASE_URL = "http://localhost:8081/api/v1/users/";

    private final static String USER_IDS = "userIds";


    public UserDataResponse toUserDataResponse(User user){
        return getUserDataResponse(user.getUserId());
    }

    public UserDataResponse toUserDataResponse(UUID userid){
        return getUserDataResponse(userid);
    }

    private UserDataResponse getUserDataResponse(UUID userid) {
        UserDataResponse followingResponse = webClient.get()
                .uri(USER_SERVICE_BASE_URL + userid)
                .retrieve()
                .bodyToMono(UserDataResponse.class)
                .block();
        if (followingResponse == null) throw new RuntimeException("Invalid");
        return followingResponse;
    }

    public List<UserDataResponse> toListOfDataResponse(List<UUID> uuidList){
        return Arrays.stream(
                Objects
                        .requireNonNull(
                                webClient.post().uri(
                                                USER_SERVICE_BASE_URL,
                                                uriBuilder -> uriBuilder.queryParam(USER_IDS, uuidList).build()
                                        ).retrieve()
                                        .bodyToMono(UserDataResponse[].class)
                                        .block())).toList();
    }

}
