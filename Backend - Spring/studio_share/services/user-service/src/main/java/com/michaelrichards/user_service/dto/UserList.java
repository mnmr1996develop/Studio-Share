package com.michaelrichards.user_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserList {

    private List<UUID> userIds;
}
