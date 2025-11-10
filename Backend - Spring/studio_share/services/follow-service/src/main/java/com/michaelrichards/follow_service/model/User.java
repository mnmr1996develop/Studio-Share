package com.michaelrichards.follow_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_user")
public class User {

    @Id
    private UUID userId;

    private Boolean isAccountPrivate;

    private String username;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FollowerRelationship> followers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FollowerRelationship> following = new LinkedHashSet<>();


}
