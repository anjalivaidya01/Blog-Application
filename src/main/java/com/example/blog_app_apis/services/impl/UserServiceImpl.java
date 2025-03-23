package com.example.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.blog_app_apis.exception.*;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.payloads.UserDto;
import com.example.blog_app_apis.repositories.UserRepository;
import com.example.blog_app_apis.services.UserService;

public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {

		System.out.println("hello world");

		System.out.println("Dhoom 5");

		System.out.println("My new hello world program");




		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId)); //lambda expression
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepository.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
		
		
		
		return userDto1;
		
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users =this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepository.delete(user);
		
	}

	private User dtoToUser(UserDto userDto)
	{
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(user.getAbout());
		user.setPassword(userDto.getPassword());
		
		
		
		return user;
		
		
		
	}
	
	public UserDto  userToDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		
		return userDto;
		
	}
	
}
