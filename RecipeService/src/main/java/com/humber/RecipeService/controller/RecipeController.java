package com.humber.RecipeService.controller;

import com.humber.RecipeService.model.Recipe;
import com.humber.RecipeService.service.RecipeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RecipeController {
    private final RecipeServiceImpl recipeService;

    @GetMapping("/recipe")
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        return ResponseEntity.ok(recipeService.getAllRecipe());
    }

    @PostMapping("/addRecipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @PutMapping("/updateRecipe")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.updateRecipeById(recipe));
    }

    @DeleteMapping("/recipe/{recipeId}")
    public ResponseEntity<Optional<Recipe>> deleteRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
    }
}
