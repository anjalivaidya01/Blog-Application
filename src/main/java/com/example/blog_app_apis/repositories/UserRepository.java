package com.example.blog_app_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.payloads.UserDto;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
	

}
