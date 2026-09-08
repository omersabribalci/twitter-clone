package com.wtech.twitter.service;

import java.util.UUID;

public interface RetweetService {
    void createRetweet(UUID tweetId, String userName);
    void deleteRetweet(UUID id, String userName);
}