package com.humber.UserService.controller;

import com.humber.UserService.model.User;
import com.humber.UserService.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getHome() {
        return "Welcome home admin.";
    }

    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/changeUserStatus/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> changeUserStatus(@PathVariable Long id) {
        boolean statusChanged = userService.changeUserStatusById(id);

        if (statusChanged) {
            return ResponseEntity.ok("User status changed successfully.");
        } else {
            return ResponseEntity.badRequest().body("User with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/deleteComment/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteComment(id));
    }

    @PostMapping("/getCommentByUserId/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getCommentByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteComment(id));
    }

    @DeleteMapping("/deleteRecipe/{recipeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long recipeId) {
        boolean recipeDeleted = userService.deleteRecipeById(recipeId);
        if (recipeDeleted) {
            return ResponseEntity.ok("Recipe deleted successfully!");
        } else {
            return ResponseEntity.badRequest().body("Error deleting recipe. Recipe with ID " + recipeId + " not found.");
        }
    }
}
