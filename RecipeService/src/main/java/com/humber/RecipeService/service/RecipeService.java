package com.humber.RecipeService.service;

import com.humber.RecipeService.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    public List<Recipe> getAllRecipe();
    public Recipe addRecipe(Recipe recipe);

    public Optional<Recipe> deleteRecipe(Long id);

    public Recipe updateRecipeById(Recipe recipe);
}
