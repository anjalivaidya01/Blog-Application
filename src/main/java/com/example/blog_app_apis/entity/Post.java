package com.example.blog_app_apis.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer postId;

    private String title;

    private String content;

    private Date addedDate;


}
