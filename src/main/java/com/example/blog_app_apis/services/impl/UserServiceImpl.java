package com.example.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.blog_app_apis.config.AppConstants;
import com.example.blog_app_apis.entity.Role;
import com.example.blog_app_apis.exception.*;
import com.example.blog_app_apis.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.payloads.UserDto;
import com.example.blog_app_apis.repositories.UserRepository;
import com.example.blog_app_apis.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {

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
		//UserDto userDto1 = this.userToDto(updateUser);
		
		
		
		return this.userToDto(updateUser);
		
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
		User user = this.modelMapper.map(userDto, User.class);
		//we are converting dto to user so mapping model of dto to user
		/*user.setId(user.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());*/
		
		
		
		return user;
		
		
		
	}
	
	public UserDto  userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user , UserDto.class);
		//we are converting user to dto so mapping model of dto to user

		/*userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());*/
		
		return userDto;
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		//roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepository.save(user);


		return this.modelMapper.map(newUser, UserDto.class);
	}
	
}
