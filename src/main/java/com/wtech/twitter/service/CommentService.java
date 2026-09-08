package com.wtech.twitter.service;

import com.wtech.twitter.dto.CommentRequest;
import com.wtech.twitter.dto.CommentResponse;

import java.util.UUID;

public interface CommentService {
    CommentResponse createComment(CommentRequest request, String userName);
    CommentResponse updateComment(UUID id, CommentRequest request, String userName);
    void deleteComment(UUID id, String userName);
}