package com.humber.CommentService.controller;

import com.humber.CommentService.entity.Comment;
import com.humber.CommentService.service.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addNewComment(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.addNewComment(comment));
    }

    @GetMapping("/getAllComments/{userId}")
    public ResponseEntity<List<Comment>> getAllCommentByUser(@PathVariable Long userId){
        return ResponseEntity.ok(commentService.getAllCommentsByUser(userId));
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
