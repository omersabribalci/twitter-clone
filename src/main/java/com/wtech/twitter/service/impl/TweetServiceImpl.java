package com.wtech.twitter.service.impl;

import com.wtech.twitter.dto.TweetRequest;
import com.wtech.twitter.dto.TweetResponse;
import com.wtech.twitter.dto.converter.TweetDtoConverter;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.exceptions.TwitterException;
import com.wtech.twitter.repository.TweetRepository;
import com.wtech.twitter.repository.UserRepository;
import com.wtech.twitter.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TweetResponse findById(UUID id) {
        Tweet tweet = tweetRepository.findById(id).orElseThrow(
                () -> new TwitterException("Tweet does not exist with this ID: " + id, HttpStatus.NOT_FOUND)
        );

        return TweetDtoConverter.convertToDto(tweet);
    }

    @Override
    public List<TweetResponse> findAllByUserId(UUID userId) {
        List<Tweet> tweets = tweetRepository.findAllByUserId(userId);
        return TweetDtoConverter.convertToDtoList(tweets);
    }

    @Override
    public TweetResponse save(TweetRequest tweetRequest, User user) {
        Tweet newTweet = TweetDtoConverter.convertToEntity(tweetRequest, user);
        Tweet savedTweet = tweetRepository.save(newTweet);
        return TweetDtoConverter.convertToDto(savedTweet);
    }

    @Override
    public TweetResponse updateTweet(UUID id, TweetRequest tweetRequest, String userName) {
        Tweet tweet = findTweetOrThrow(id);

        if (!tweet.getUser().getUserName().equals(userName)) {
            throw new TwitterException("Not authorized!", HttpStatus.FORBIDDEN);
        }

        tweet.setContent(tweetRequest.getContent());
        return TweetDtoConverter.convertToDto(tweetRepository.save(tweet));
    }

    @Override
    public void deleteTweet(UUID id, String userName) {
        Tweet tweet = findTweetOrThrow(id);

        if (!tweet.getUser().getUserName().equals(userName)) {
            throw new TwitterException("Not authorized!", HttpStatus.FORBIDDEN);
        }

        tweetRepository.delete(tweet);
    }

    private Tweet findTweetOrThrow(UUID id) {
        return tweetRepository.findById(id).orElseThrow(
                () -> new TwitterException("Tweet does not exist with this ID: " + id, HttpStatus.NOT_FOUND)
        );
    }



}
