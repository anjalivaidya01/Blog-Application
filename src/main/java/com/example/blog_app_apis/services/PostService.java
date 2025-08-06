package com.example.blog_app_apis.services;

import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.payloads.PostDto;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update

    Post updatePost(PostDto postDto , Integer postId);


    //delete

    void deletePost(Integer postId);

    //get all posts

    List<Post> getAllPost();

    //get single post

    Post getPostById(Integer postId);

    //get all post by category

    List<Post> getPostByCategory(Integer categoryId);

    //get all posts by user

    List<Post> getPostsBtUser(Integer userId);

    //search posts

    List<Post> searchPosts(String keyword);




}
