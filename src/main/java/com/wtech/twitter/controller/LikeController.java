package com.wtech.twitter.controller;

import com.wtech.twitter.dto.ApiResponse;
import com.wtech.twitter.dto.LikeRequest;
import com.wtech.twitter.service.LikeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like")
    public ApiResponse<Void> likeTweet(@Valid @RequestBody LikeRequest request,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        likeService.likeTweet(request.getTweetId(), userDetails.getUsername());
        return ApiResponse.success("Tweet liked successfully!", null, HttpStatus.OK.value());
    }

    @PostMapping("/dislike")
    public ApiResponse<Void> unlikeTweet(@Valid @RequestBody LikeRequest request,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        likeService.unlikeTweet(request.getTweetId(), userDetails.getUsername());
        return ApiResponse.success("Tweet unliked successfully!", null, HttpStatus.OK.value());
    }
}