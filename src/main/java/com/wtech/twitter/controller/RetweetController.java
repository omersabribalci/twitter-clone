package com.wtech.twitter.controller;

import com.wtech.twitter.dto.ApiResponse;
import com.wtech.twitter.dto.RetweetRequest;
import com.wtech.twitter.service.RetweetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private final RetweetService retweetService;

    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public ApiResponse<Void> createRetweet(@Valid @RequestBody RetweetRequest request,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        retweetService.createRetweet(request.getTweetId(), userDetails.getUsername());
        return ApiResponse.success("Tweet retweeted successfully!", null, HttpStatus.CREATED.value());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRetweet(@PathVariable UUID id,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        retweetService.deleteRetweet(id, userDetails.getUsername());
        return ApiResponse.success("Retweet deleted successfully!", null, HttpStatus.OK.value());
    }
}