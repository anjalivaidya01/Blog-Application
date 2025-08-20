package com.example.blog_app_apis.repositories;

import com.example.blog_app_apis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer>{
}
