package com.example.blog_app_apis.repositories;

import com.example.blog_app_apis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
