package com.wtech.twitter.repository;

import com.wtech.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {
    // oto query var..
    List<Tweet> findAllByUserId(UUID userId);
}
