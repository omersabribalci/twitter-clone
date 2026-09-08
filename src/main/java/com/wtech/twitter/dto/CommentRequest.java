package com.wtech.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentRequest {
    @NotBlank(message = "Content cannot be empty!")
    @Size(max = 280, message = "Max 280 char!")
    private String content;

    @NotNull(message = "tweetId cannot be null!")
    private UUID tweetId;
}
