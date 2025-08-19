package com.example.blog_app_apis.services.impl;

import com.example.blog_app_apis.entity.Comment;
import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.exception.ResourceNotFoundException;
import com.example.blog_app_apis.payloads.CommentDto;
import com.example.blog_app_apis.repositories.CommentRepo;
import com.example.blog_app_apis.repositories.PostReop;
import com.example.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostReop postReop;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postReop.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment saveComment = this.commentRepo.save(comment);

        return this.modelMapper.map(saveComment, CommentDto.class);

    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
        this.commentRepo.delete(com);
    }
}
