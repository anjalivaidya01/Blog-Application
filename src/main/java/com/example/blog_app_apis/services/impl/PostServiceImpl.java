package com.example.blog_app_apis.services.impl;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.exception.ResourceNotFoundException;
import com.example.blog_app_apis.payloads.PostDto;
import com.example.blog_app_apis.payloads.PostResponse;
import com.example.blog_app_apis.repositories.CategoryRepo;
import com.example.blog_app_apis.repositories.PostReop;
import com.example.blog_app_apis.repositories.UserRepository;
import com.example.blog_app_apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postReop.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());


       Post updatedPost = this.postReop.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

       Post post = this.postReop.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id", postId));

       this.postReop.delete(post);
    }

    @Override
   // public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
        public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();

        }else
        {
            sort = Sort.by(sortBy).descending();
        }


        Pageable p = PageRequest.of(pageNumber, pageSize, sort);



        Page<Post> pagePost = this.postReop.findAll(p);

        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post) ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());


        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);

        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());




        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postReop.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id", categoryId));
        List<Post> posts = this.postReop.findByCategory(cat);

       List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> posts = this.postReop.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keywords) {
       List<Post> posts = this.postReop.findByTitleContaining(keywords);
       List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }
}
