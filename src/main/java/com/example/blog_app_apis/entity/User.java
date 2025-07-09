package com.example.blog_app_apis.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	
	private int id;
	
	@Column(name = "user_name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	
	@Column(name = "about", nullable = false, length = 100)
	private String about;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
}
