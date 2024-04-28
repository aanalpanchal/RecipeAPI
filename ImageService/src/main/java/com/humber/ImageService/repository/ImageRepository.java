package com.humber.ImageService.repository;

import com.humber.ImageService.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByImageId(Long id);

    Optional<Image> findByRecipeId(Long recipeId);
}
