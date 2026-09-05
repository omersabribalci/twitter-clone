package com.wtech.twitter.service.impl;

import com.wtech.twitter.dto.TweetRequest;
import com.wtech.twitter.dto.TweetResponse;
import com.wtech.twitter.dto.converter.TweetDtoConverter;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.exceptions.TwitterException;
import com.wtech.twitter.repository.TweetRepository;
import com.wtech.twitter.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
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


}
