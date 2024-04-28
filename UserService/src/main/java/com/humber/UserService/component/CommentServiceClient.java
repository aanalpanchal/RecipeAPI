package com.humber.UserService.component;

import com.humber.UserService.model.Comment;
import com.humber.UserService.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class CommentServiceClient {

    @Value("${CommentService.addNewComment}")
    private String addNewComment;

    @Value("${CommentService.deleteComment}")
    private String deleteComment;

    @Value("${CommentService.getAllCommentByUser}")
    private String getCommentByUserId;

    private final RestTemplate restTemplate;

    public CommentServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Comment addNewComment(Comment comment){
        log.info("addNewComment: " + addNewComment);
        log.info("comment: " + comment);
        Comment commentDetail = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Comment> request = new HttpEntity<>(comment, headers);
            commentDetail = restTemplate.postForObject(addNewComment, request, Comment.class);
            log.info("commentDetail: " + commentDetail);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return commentDetail;
    }

    public String deleteComment(Long commentId) {
        log.info("deleteComment: " + deleteComment);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String deleteUrl = deleteComment.replace("{commentId}", commentId.toString());

            restTemplate.delete(deleteUrl);

            log.info("Comment deleted successfully.");
            return "Comment deleted successfully.";
        } catch (Exception ex) {
            log.error("Error deleting comment: " + ex.getMessage());
            return "Error deleting comment: " + ex.getMessage();
        }
    }

    public List<Comment> getCommentByUserId(Long userId) {
        log.info("getCommentByUserId: " + getCommentByUserId);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String url = getCommentByUserId + "?userId=" + userId;
            ResponseEntity<List<Comment>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Comment>>() {}
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                log.error("Failed to fetch comments for user with ID " + userId + ". Status code: " + response.getStatusCodeValue());
                return Collections.emptyList();
            }
        } catch (Exception ex) {
            log.error("Error occurred while fetching comments for user with ID " + userId + ": " + ex.getMessage());
            return Collections.emptyList();
        }
    }
}
