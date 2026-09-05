package com.wtech.twitter.dto.converter;


import com.wtech.twitter.dto.TweetRequest;
import com.wtech.twitter.dto.TweetResponse;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class TweetDtoConverter {
    public static TweetResponse convertToDto(Tweet tweet) {
        if (tweet == null) {
            return null;
        }

        return new TweetResponse(tweet.getId(), tweet.getContent(),
                tweet.getCreatedAt(), tweet.getUser().getUserName(),
                tweet.getUser().getName(), tweet.getUser().getPhoto());
    }

    public static List<TweetResponse> convertToDtoList(List<Tweet> tweets) {
        List<TweetResponse> tweetResponses = new ArrayList<>();

        for (Tweet tweet: tweets) {
            tweetResponses.add(convertToDto(tweet));
        }

        return tweetResponses;
    }

    public static Tweet convertToEntity(TweetRequest tweetRequest, User user) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.getContent());
        tweet.setUser(user);
        return tweet;
    }
}
