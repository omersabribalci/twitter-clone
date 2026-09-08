package com.wtech.twitter.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(UUID id, String content, LocalDateTime createdAt,
                              String userName, String name, String photo, UUID tweetId) {
}