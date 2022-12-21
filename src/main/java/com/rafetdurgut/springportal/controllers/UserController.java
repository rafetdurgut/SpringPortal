package com.rafetdurgut.springportal.controllers;

import com.rafetdurgut.springportal.models.Post;
import com.rafetdurgut.springportal.models.User;
import com.rafetdurgut.springportal.services.PostService;
import com.rafetdurgut.springportal.services.UserRoleService;
import com.rafetdurgut.springportal.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    public final PostService postService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        User new_user = userService.saveUser(user);
        System.out.println(new_user);
        return ResponseEntity.created(uri).body(new_user);
    }

    @PostMapping("/users/roles")
    public ResponseEntity<?> addRoleToUser(@RequestBody UserRoleForm form)
    {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok("Successful!");
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> addPost(@PathVariable Long id, @RequestBody Post post){
        post.setUser( userService.getUserById(id) );
        Post added = postService.addPost(post);
        return new ResponseEntity(added, HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}/posts")
    public ResponseEntity<Post> getPosts(@PathVariable Long id){
        return new ResponseEntity(postService.getAllPostByUserId(id), HttpStatus.OK);
    }
}
@Data
class UserRoleForm
{
    private String username;
    private String roleName;
}
