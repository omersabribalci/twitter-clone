package com.wtech.twitter.repository;

import com.wtech.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {
    @Query("SELECT t FROM Tweet t WHERE t.user.id = :userId")
    List<Tweet> findAllByUserId(@Param("userId") UUID userId);
}
