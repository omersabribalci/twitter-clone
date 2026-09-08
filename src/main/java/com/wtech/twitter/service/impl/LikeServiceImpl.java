package com.wtech.twitter.service.impl;

import com.wtech.twitter.entity.Like;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.exceptions.TwitterException;
import com.wtech.twitter.repository.LikeRepository;
import com.wtech.twitter.repository.TweetRepository;
import com.wtech.twitter.repository.UserRepository;
import com.wtech.twitter.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public LikeServiceImpl(LikeRepository likeRepository,
                           TweetRepository tweetRepository,
                           UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void likeTweet(UUID tweetId, String userName) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(
                () -> new TwitterException("Tweet does not exist with this ID: " + tweetId, HttpStatus.NOT_FOUND)
        );

        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new TwitterException("User does not exist with this username: " + userName, HttpStatus.NOT_FOUND)
        );

        likeRepository.findByUserIdAndTweetId(user.getId(), tweetId).ifPresent(l -> {
            throw new TwitterException("Already liked!", HttpStatus.CONFLICT);
        });

        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);
        likeRepository.save(like);
    }

    @Override
    public void unlikeTweet(UUID tweetId, String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new TwitterException("User does not exist with this username: " + userName, HttpStatus.NOT_FOUND)
        );

        Like like = likeRepository.findByUserIdAndTweetId(user.getId(), tweetId).orElseThrow(
                () -> new TwitterException("Already disliked.", HttpStatus.NOT_FOUND)
        );

        likeRepository.delete(like);
    }
}