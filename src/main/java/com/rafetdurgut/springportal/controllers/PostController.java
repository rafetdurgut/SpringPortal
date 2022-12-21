package com.rafetdurgut.springportal.controllers;


import com.rafetdurgut.springportal.models.Comment;
import com.rafetdurgut.springportal.models.Post;
import com.rafetdurgut.springportal.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    public PostService postService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/comments")
    public ResponseEntity<Post> addComment(@PathVariable Long id, @RequestBody Comment comment){

        Post added = postService.addComment(id, comment);
        return new ResponseEntity(added, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPost(){
        return new ResponseEntity(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Post>> getPostById(@PathVariable Long id){
        return new ResponseEntity(postService.getById(id), HttpStatus.OK);
    }

}
