package com.wtech.twitter.controller;

import com.wtech.twitter.dto.ApiResponse;
import com.wtech.twitter.dto.TweetRequest;
import com.wtech.twitter.dto.TweetResponse;
import com.wtech.twitter.dto.UserResponse;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.repository.UserRepository;
import com.wtech.twitter.service.TweetService;
import com.wtech.twitter.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;
    private final UserService userService;


    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    // İstek URL'si: GET /tweet/findById?id=123e4567-e89b-12d3-a456-426614174000
    @GetMapping("/findById")
    public ApiResponse<TweetResponse> findById(@RequestParam UUID id) {
        TweetResponse tweetResponse = tweetService.findById(id);
        return ApiResponse.success("Tweet is shown successfully!", tweetResponse, HttpStatus.OK.value());
    }

    // İstek URL'si: GET /tweet/findByUserId?userId=123e4567-e89b-12d3-a456-426614174000
    @GetMapping("/findByUserId")
    public ApiResponse<List<TweetResponse>> findAllByUserId(@RequestParam UUID userId) {
        UserResponse userResponse = userService.findById(userId); // bu id li kullanıcı var mı?
        List<TweetResponse> tweetResponses = tweetService.findAllByUserId(userId);
        return ApiResponse.success("User's all tweets are shown successfully!", tweetResponses, HttpStatus.OK.value());
    }

    @PostMapping
    public ApiResponse<TweetResponse> createTweet(@Valid @RequestBody TweetRequest tweetRequest,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserEntityByUserName(userDetails.getUsername());
        TweetResponse tweetResponse = tweetService.save(tweetRequest, user);
        return ApiResponse.success("Tweet created successfully!", tweetResponse, HttpStatus.OK.value());
    }

}
