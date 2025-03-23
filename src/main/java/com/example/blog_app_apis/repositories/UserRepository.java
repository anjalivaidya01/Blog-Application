package com.example.blog_app_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.payloads.UserDto;

public interface UserRepository extends JpaRepository<User, Integer> {

	

}
