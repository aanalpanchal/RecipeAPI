package com.humber.CommentService.service;

import com.humber.CommentService.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    public List<Comment> getAllCommentsByUser(Long UserId);
    public Comment addNewComment(Comment comment);
    public String deleteComment(Long commentId);
    public Optional<Comment> getCommentById(Long commentId);
}
