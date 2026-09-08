package com.wtech.twitter.repository;

import com.wtech.twitter.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findByUserIdAndTweetId(UUID userId, UUID tweetId);
}