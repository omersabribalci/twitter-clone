package com.wtech.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetRequest {
    @NotBlank(message = "Content cannot be empty!")
    @Size(max = 280, message = "Max 280 char!")
    private String content;
}
