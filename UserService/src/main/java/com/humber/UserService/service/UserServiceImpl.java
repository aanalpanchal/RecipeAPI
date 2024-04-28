package com.humber.UserService.service;

import com.humber.UserService.component.CommentServiceClient;
import com.humber.UserService.component.ImageServiceClient;
import com.humber.UserService.component.RecipeServiceClient;
import com.humber.UserService.config.MyUserDetail;
import com.humber.UserService.model.Comment;
import com.humber.UserService.model.Image;
import com.humber.UserService.model.Recipe;
import com.humber.UserService.model.User;
import com.humber.UserService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RecipeServiceClient serviceClient;
    private final PasswordEncoder passwordEncoder;
    private final ImageServiceClient imageServiceClient;
    private final CommentServiceClient commentServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found!!!");
        }
        return new MyUserDetail(user.get());
    }

    public List<Recipe> getAllRecipe() {
        return serviceClient.getAllRecipe();
    }
    public List<User> getAllUsers() {
        return userRepository.findUserByRole("ROLE_USER");
    }

    public void addNewRecipe(Recipe recipe) {
        Recipe addedRecipe = serviceClient.addRecipeDetail(recipe);
        //addedRecipe.setImage(imageServiceClient.addImage(image));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        System.out.println("current username " + currentUsername);
        User currentUser = userRepository.findByUsername(currentUsername).get();

        if (currentUser != null) {
            currentUser.getRecipes().add(addedRecipe);
            userRepository.save(currentUser);
        }
    }

    public void updateNewRecipe(Recipe recipe) {
        Recipe updatedRecipe = serviceClient.updateRecipeDetail(recipe);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername).get();

        if (currentUser != null) {
            currentUser.getRecipes().add(updatedRecipe);

            userRepository.save(currentUser);
        }
    }

    public Boolean deleteRecipeById(Long recipeId) {
        return serviceClient.deleteRecipeDetail(recipeId);
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean changeUserStatusById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            boolean currentStatus = user.getIsActive();

            user.setIsActive(!currentStatus);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public String deleteComment(Long commentId){
        return commentServiceClient.deleteComment(commentId);
    }

    public List<Comment> getCommentByUserId(Long commentId){
        return commentServiceClient.getCommentByUserId(commentId);
    }

    public Comment addNewComment(Comment comment){
        return commentServiceClient.addNewComment(comment);
    }
}
