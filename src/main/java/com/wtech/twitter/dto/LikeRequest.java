package com.wtech.twitter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LikeRequest {
    @NotNull(message = "tweetId cannot be null!")
    private UUID tweetId;
}