package com.example.blog_app_apis.controller;

import com.example.blog_app_apis.config.AppConstants;
import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.payloads.ApiResponse;
import com.example.blog_app_apis.payloads.PostDto;
import com.example.blog_app_apis.payloads.PostResponse;
import com.example.blog_app_apis.services.FileService;
import com.example.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;


    @Value("$(project.image)")
    private String path;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){

     PostDto createPost =    this.postService.createPost(postDto,userId,categoryId);
     return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(
            @PathVariable Integer userId
    ){
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }


    //get by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable Integer categoryId
    ){
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }


    //get all post

    @GetMapping("/posts")
    //public ResponseEntity<List<PostDto>> getAllPost(
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
       // List<PostDto> allPost = this.postService.getAllPost(pageNumber, pageSize);

       PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

       // return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }

    // get post detail by id

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){


       PostDto postDto =  this.postService.getPostById(postId);
       return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //update
    //delete
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId){

        PostDto updatePost = this.postService.updatePost(postDto ,postId);

        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){

        this.postService.deletePost(postId);
        return new ApiResponse("post is success fully deleted", true);

    }

    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable("keywords") String keywords
    ){
        List<PostDto> result = this.postService.searchPosts(keywords);

        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

    }




    //post image upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);

       postDto.setImageName(fileName);
       PostDto updatePost = this.postService.updatePost(postDto,postId);
       return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);


    }
}
