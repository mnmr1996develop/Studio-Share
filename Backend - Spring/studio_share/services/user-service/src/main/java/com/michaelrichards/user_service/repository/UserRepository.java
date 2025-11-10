package com.michaelrichards.user_service.repository;

import com.michaelrichards.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    List<User> findByUsernameLike(String username);

    Optional<User> findByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCase(String username);

    @Query("select u from User u where u.firstName like ?1 or u.lastName like ?1 or u.username = ?1")
    Optional<User> searchUser(String searchQuery);

    @Query("select u from User u where u.userId in ?1")
    List<User> findByUserIdIn(List<UUID> userIds);


}
