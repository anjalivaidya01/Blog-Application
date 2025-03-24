package com.example.blog_app_apis.controller;

import com.example.blog_app_apis.payloads.UserDto;
import com.example.blog_app_apis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST--create user
    @PostMapping("/")
    public ResponseEntity<UserDto> creteUser(@RequestBody UserDto userDto){
        System.out.println("create user controller");
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        System.out.println("get all user controller");
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //GET -- user get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){

        System.out.println("get user controller");

        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
