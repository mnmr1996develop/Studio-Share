package com.michaelrichards.user_service.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PrivacySetting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long privacyId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_user_id")
    private User user;

    private Boolean isAccountPrivate;

}
