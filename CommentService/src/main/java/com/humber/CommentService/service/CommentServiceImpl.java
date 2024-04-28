package com.humber.CommentService.service;

import com.humber.CommentService.entity.Comment;
import com.humber.CommentService.exception.CommentNotFoundException;
import com.humber.CommentService.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllCommentsByUser(Long UserId) {
        return commentRepository.getAllByUserUserId(UserId);
    }

    @Override
    public Comment addNewComment(Comment comment) {
        comment.setDateTime(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public String deleteComment(Long commentId) {
        Optional<Comment> comment = getCommentById(commentId);
        if(comment.isPresent()){
            commentRepository.deleteById(commentId);
            return "deleted successfully";
        }
        throw new CommentNotFoundException("Comment not found");
    }

    @Override
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }
}
