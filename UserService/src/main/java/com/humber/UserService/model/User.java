package com.humber.UserService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private Boolean isActive;
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_user_id", referencedColumnName = "userId")
    private List<Recipe> recipes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_user_id", referencedColumnName = "userId")
    private List<Comment> comments;
}
