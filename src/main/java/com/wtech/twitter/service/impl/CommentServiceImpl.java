package com.wtech.twitter.service.impl;

import com.wtech.twitter.dto.CommentRequest;
import com.wtech.twitter.dto.CommentResponse;
import com.wtech.twitter.dto.converter.CommentDtoConverter;
import com.wtech.twitter.entity.Comment;
import com.wtech.twitter.entity.Tweet;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.exceptions.TwitterException;
import com.wtech.twitter.repository.CommentRepository;
import com.wtech.twitter.repository.TweetRepository;
import com.wtech.twitter.repository.UserRepository;
import com.wtech.twitter.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              TweetRepository tweetRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentResponse createComment(CommentRequest request, String userName) {
        Tweet tweet = tweetRepository.findById(request.getTweetId()).orElseThrow(
                () -> new TwitterException("Tweet does not exist with this ID: " + request.getTweetId(), HttpStatus.NOT_FOUND)
        );

        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new TwitterException("User does not exist with this username: " + userName, HttpStatus.NOT_FOUND)
        );

        Comment comment = CommentDtoConverter.convertToEntity(request, user, tweet);
        return CommentDtoConverter.convertToDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponse updateComment(UUID id, CommentRequest request, String userName) {
        Comment comment = findCommentOrThrow(id);

        if (!comment.getUser().getUserName().equals(userName)) {
            throw new TwitterException("Not authorized!", HttpStatus.FORBIDDEN);
        }

        comment.setContent(request.getContent());
        return CommentDtoConverter.convertToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(UUID id, String userName) {
        Comment comment = findCommentOrThrow(id);

        boolean isCommentOwner = comment.getUser().getUserName().equals(userName);
        boolean isTweetOwner = comment.getTweet().getUser().getUserName().equals(userName);

        if (!isCommentOwner && !isTweetOwner) {
            throw new TwitterException("Not authorized!", HttpStatus.FORBIDDEN);
        }

        commentRepository.delete(comment);
    }

    private Comment findCommentOrThrow(UUID id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new TwitterException("Comment does not exist with this ID: " + id, HttpStatus.NOT_FOUND)
        );
    }
}