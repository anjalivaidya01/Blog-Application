package com.example.blog_app_apis.repositories;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostReop extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.title like %:key%")
    List<Post> searchByTitle(@Param("key") String title);
}
