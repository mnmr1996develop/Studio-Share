package com.michaelrichards.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@Table(name = "m_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID userId;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email(message = "Must be a valid email")
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String username;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime lastUpdated;


    @Size(min = 8)
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    @Past(message = "Invalid Birthday")
    private LocalDate birthday;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private PrivacySetting privacySetting;

    @Transient
    public Integer getAge() {
        if (this.birthday == null) {
            return null; // Or throw an exception if birthday is mandatory
        }
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }
}
