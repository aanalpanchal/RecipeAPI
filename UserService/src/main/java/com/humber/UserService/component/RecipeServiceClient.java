package com.humber.UserService.component;

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
public class RecipeServiceClient {
    @Value("${recipeService.getAllRecipeUrlTemplate}")
    private String getAllRecipeUrlTemplate;

    @Value("${recipeService.addRecipeUrlTemplate}")
    private String addRecipeUrlTemplate;

    @Value("${recipeService.updateRecipeUrlTemplate}")
    private String updateRecipeUrlTemplate;

    @Value("${recipeService.deleteRecipeUrlTemplate}")
    private String deleteRecipeUrlTemplate;

    private final RestTemplate restTemplate;

    public RecipeServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Recipe> getAllRecipe() {
        log.info("getAllRecipeUrlTemplate: " + getAllRecipeUrlTemplate);

        ResponseEntity<List<Recipe>> response = restTemplate.exchange(
                getAllRecipeUrlTemplate,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Recipe>>() {}
        );

        return response.getBody();
    }

    public Recipe addRecipeDetail(Recipe recipe) {
        log.info("addRecipeUrlTemplate: " + addRecipeUrlTemplate);
        log.info("recipe: " + recipe);

        Recipe recipeDetails = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Recipe> request = new HttpEntity<>(recipe, headers);
            recipeDetails = restTemplate.postForObject(addRecipeUrlTemplate, request, Recipe.class);
            log.info("recipeDetails: " + recipeDetails);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return recipeDetails;
    }

    public Recipe updateRecipeDetail(Recipe recipe) {
        log.info("recipe info: " + recipe);
        log.info("updateRecipeUrlTemplate: " + updateRecipeUrlTemplate);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Recipe> requestEntity = new HttpEntity<>(recipe, headers);

            ResponseEntity<Recipe> responseEntity = restTemplate.exchange(
                    updateRecipeUrlTemplate,
                    HttpMethod.PUT,
                    requestEntity,
                    Recipe.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public Boolean deleteRecipeDetail(Long recipeId) {
        log.info("deleteRecipeUrlTemplate: " + deleteRecipeUrlTemplate);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String deleteUrl = deleteRecipeUrlTemplate.replace("{recipeId}", recipeId.toString());

            restTemplate.delete(deleteUrl);

            log.info("Recipe deleted successfully.");
            return true;
        } catch (Exception ex) {
            log.error("Error deleting recipe: " + ex.getMessage());
            return false;
        }
    }
}
