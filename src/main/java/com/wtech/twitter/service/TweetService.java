package com.wtech.twitter.service;

import com.wtech.twitter.dto.TweetRequest;
import com.wtech.twitter.dto.TweetResponse;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;

import java.util.List;
import java.util.UUID;

public interface TweetService {
    TweetResponse findById(UUID id);
    List<TweetResponse> findAllByUserId(UUID userId);
    TweetResponse save(TweetRequest tweetRequest, User user);
    TweetResponse updateTweet(UUID id, TweetRequest request, String userName);
    void deleteTweet(UUID id, String userName);
}
