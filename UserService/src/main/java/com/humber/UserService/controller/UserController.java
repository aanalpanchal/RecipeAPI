package com.humber.UserService.controller;

import com.humber.UserService.component.RecipeServiceClient;
import com.humber.UserService.model.Comment;
import com.humber.UserService.model.Recipe;
import com.humber.UserService.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "javainuseapi")
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getHome() {
        return "Welcome user";
    }

    @GetMapping("/recipes")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Recipe> getRecipes() {
        return userService.getAllRecipe();
    }

    @PostMapping(value = "/addRecipe")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) throws IOException {
        userService.addNewRecipe(recipe);
        return ResponseEntity.ok("Recipe added successfully.");
    }

    @PutMapping("/updateRecipe")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe) {
        userService.updateNewRecipe(recipe);
        return ResponseEntity.ok("Recipe updated successfully.");
    }

    @PostMapping("/addComment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        return ResponseEntity.ok(userService.addNewComment(comment));
    }
}
