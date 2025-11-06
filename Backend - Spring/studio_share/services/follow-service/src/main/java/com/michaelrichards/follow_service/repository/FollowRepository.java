package com.michaelrichards.follow_service.repository;

import com.michaelrichards.follow_service.model.FollowerRelationship;
import com.michaelrichards.follow_service.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<FollowerRelationship, UUID> {

    Optional<FollowerRelationship> findByFollowerAndFollowing(User follower, User following);


    @Query("SELECT fr FROM FollowerRelationship fr JOIN FETCH fr.following WHERE fr.follower.userId = :userId")
    List<FollowerRelationship> findFollowingByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT fr FROM FollowerRelationship fr JOIN FETCH fr.follower WHERE fr.following.userId = :userId")
    List<FollowerRelationship> findFollowersByUserId(@Param("userId") UUID userId, Pageable pageable);



    void deleteByFollowerAndFollowing(User follower, User following);

    @Query("SELECT COUNT(fr) FROM FollowerRelationship fr WHERE fr.follower.userId = :userId")
    Long countFollowingByUserId(@Param("userId") UUID userId);

    @Query("SELECT COUNT(fr) FROM FollowerRelationship fr WHERE fr.following.userId = :userId")
    Long countFollowersByUserId(@Param("userId") UUID userId);

    boolean existsByFollower_UserIdAndFollowing_UserId(UUID follower, UUID following);

}
