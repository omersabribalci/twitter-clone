package com.wtech.twitter.service;

import java.util.UUID;

public interface LikeService {
    void likeTweet(UUID tweetId, String userName);
    void unlikeTweet(UUID tweetId, String userName);
}