package com.humber.UserService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;
    private String name;
    private String description;

    @JsonBackReference
    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imageId")
    private Image image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentId")
    private Comment comment;
}
