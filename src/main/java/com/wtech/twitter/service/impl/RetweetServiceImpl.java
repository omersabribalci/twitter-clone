package com.wtech.twitter.service.impl;

import com.wtech.twitter.entity.Retweet;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.exceptions.TwitterException;
import com.wtech.twitter.repository.RetweetRepository;
import com.wtech.twitter.repository.TweetRepository;
import com.wtech.twitter.repository.UserRepository;
import com.wtech.twitter.service.RetweetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public RetweetServiceImpl(RetweetRepository retweetRepository,
                              TweetRepository tweetRepository,
                              UserRepository userRepository) {
        this.retweetRepository = retweetRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRetweet(UUID tweetId, String userName) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(
                () -> new TwitterException("Tweet does not exist with this ID: " + tweetId, HttpStatus.NOT_FOUND)
        );

        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new TwitterException("User does not exist with this username: " + userName, HttpStatus.NOT_FOUND)
        );

        retweetRepository.findByUserIdAndTweetId(user.getId(), tweetId).ifPresent(r -> {
            throw new TwitterException("Already retweeted!", HttpStatus.CONFLICT);
        });

        Retweet retweet = new Retweet();
        retweet.setUser(user);
        retweet.setTweet(tweet);
        retweetRepository.save(retweet);
    }

    @Override
    public void deleteRetweet(UUID id, String userName) {
        Retweet retweet = retweetRepository.findById(id).orElseThrow(
                () -> new TwitterException("Retweet does not exist with this ID: " + id, HttpStatus.NOT_FOUND)
        );

        if (!retweet.getUser().getUserName().equals(userName)) {
            throw new TwitterException("Not authorized!", HttpStatus.FORBIDDEN);
        }

        retweetRepository.delete(retweet);
    }
}