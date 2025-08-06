package com.example.blog_app_apis.controller;

import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.payloads.PostDto;
import com.example.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PostController {


    @Autowired
    private PostService postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){

     PostDto createPost =    this.postService.createPost(postDto,userId,categoryId);
     return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }
}
