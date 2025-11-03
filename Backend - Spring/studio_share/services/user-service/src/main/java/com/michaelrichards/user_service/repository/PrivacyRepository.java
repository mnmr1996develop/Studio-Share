package com.michaelrichards.user_service.repository;

import com.michaelrichards.user_service.model.PrivacySetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyRepository extends JpaRepository<PrivacySetting, Long> {
}
