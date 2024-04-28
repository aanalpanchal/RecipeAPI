package com.humber.UserService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CommentId;
    private String description;
    private LocalDateTime dateTime;
    @JsonBackReference
    @ManyToOne
    private User user;
    @JsonBackReference
    @OneToOne(mappedBy = "comment")
    private Recipe recipe;
}
