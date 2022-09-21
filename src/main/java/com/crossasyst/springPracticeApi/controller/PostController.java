package com.crossasyst.springPracticeApi.controller;

import com.crossasyst.springPracticeApi.model.PostRequest;
import com.crossasyst.springPracticeApi.model.PostResponse;
import com.crossasyst.springPracticeApi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> createPosts(@RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.createPosts(postRequest);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRequest[]> getPosts() {

        return postService.getPosts();
    }

    @GetMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PostResponse getById(@PathVariable Long id) {

        return postService.getById(id);
    }

    @PutMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> updatePosts(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.updatePosts(id, postRequest);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        postService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}