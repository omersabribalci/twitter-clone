package com.wtech.twitter.repository;

import com.wtech.twitter.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RetweetRepository extends JpaRepository<Retweet, UUID> {
    Optional<Retweet> findByUserIdAndTweetId(UUID userId, UUID tweetId);
}