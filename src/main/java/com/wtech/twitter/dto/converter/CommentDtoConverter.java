package com.wtech.twitter.dto.converter;

import com.wtech.twitter.dto.CommentRequest;
import com.wtech.twitter.dto.CommentResponse;
import com.wtech.twitter.entity.Comment;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;

public class CommentDtoConverter {
    public static CommentResponse convertToDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        return new CommentResponse(comment.getId(), comment.getContent(),
                comment.getCreatedAt(), comment.getUser().getUserName(),
                comment.getUser().getName(), comment.getUser().getPhoto(),
                comment.getTweet().getId());
    }

    public static Comment convertToEntity(CommentRequest request, User user, Tweet tweet) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setTweet(tweet);
        return comment;
    }
}