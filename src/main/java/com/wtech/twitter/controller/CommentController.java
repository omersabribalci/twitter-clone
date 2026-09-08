package com.wtech.twitter.controller;

import com.wtech.twitter.dto.ApiResponse;
import com.wtech.twitter.dto.CommentRequest;
import com.wtech.twitter.dto.CommentResponse;
import com.wtech.twitter.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ApiResponse<CommentResponse> createComment(@Valid @RequestBody CommentRequest request,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        CommentResponse commentResponse = commentService.createComment(request, userDetails.getUsername());
        return ApiResponse.success("Comment created successfully!", commentResponse, HttpStatus.CREATED.value());
    }

    @PutMapping("/{id}")
    public ApiResponse<CommentResponse> updateComment(@PathVariable UUID id,
                                                      @Valid @RequestBody CommentRequest request,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        CommentResponse commentResponse = commentService.updateComment(id, request, userDetails.getUsername());
        return ApiResponse.success("Comment updated successfully!", commentResponse, HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteComment(@PathVariable UUID id,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(id, userDetails.getUsername());
        return ApiResponse.success("Comment deleted successfully!", null, HttpStatus.OK.value());
    }
}