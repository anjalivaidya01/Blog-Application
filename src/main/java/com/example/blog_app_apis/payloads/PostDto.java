package com.example.blog_app_apis.payloads;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private User user;

    private Category category;
}
