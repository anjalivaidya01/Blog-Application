package com.example.blog_app_apis.services.impl;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.exception.ResourceNotFoundException;
import com.example.blog_app_apis.payloads.PostDto;
import com.example.blog_app_apis.repositories.CategoryRepo;
import com.example.blog_app_apis.repositories.PostReop;
import com.example.blog_app_apis.repositories.UserRepository;
import com.example.blog_app_apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostReop postReop;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);


         Post newPost = this.postReop.save(post);
        return this.modelMapper.map(newPost,PostDto.class );
    }



    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return List.of();
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostByCategory(Integer categoryId) {
        return List.of();
    }

    @Override
    public List<Post> getPostsBtUser(Integer userId) {
        return List.of();
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return List.of();
    }
}
