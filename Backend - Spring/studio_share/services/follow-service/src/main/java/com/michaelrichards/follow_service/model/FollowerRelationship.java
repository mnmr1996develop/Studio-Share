package com.michaelrichards.follow_service.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowerRelationship {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID followRelationId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime timeFollowed;

    @PrePersist
    protected void onCreate() {
        timeFollowed = LocalDateTime.now();
    }
}
