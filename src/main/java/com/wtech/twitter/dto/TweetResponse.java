package com.wtech.twitter.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TweetResponse(UUID id, String content, LocalDateTime createdAt,
                            String userName, String name, String photo) {
}
