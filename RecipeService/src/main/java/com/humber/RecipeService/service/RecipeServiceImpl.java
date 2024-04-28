package com.humber.RecipeService.service;

import com.humber.RecipeService.model.Recipe;
import com.humber.RecipeService.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Optional<Recipe> deleteRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()) {
            recipeRepository.deleteById(id);
        }
        return recipe;
    }

    @Override
    public Recipe updateRecipeById(Recipe recipe) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipe.getRecipeId());

        if (optionalRecipe.isPresent()) {
            Recipe existingRecipe = optionalRecipe.get();
            existingRecipe.setUserUserId(recipe.getUserUserId());
            existingRecipe.setName(recipe.getName());
            existingRecipe.setDescription(recipe.getDescription());
            return recipeRepository.save(existingRecipe);
        } else {
            return null;
        }
    }
}
