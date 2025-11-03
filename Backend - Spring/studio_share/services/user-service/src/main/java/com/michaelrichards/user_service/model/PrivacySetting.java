package com.michaelrichards.user_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PrivacySetting {

    @Id
    private Long privacyId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_user_id")
    private User user;

    private Boolean isAccountPrivate;

}
