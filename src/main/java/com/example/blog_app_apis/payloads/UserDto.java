package com.example.blog_app_apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;

	@NonNull
	@Size(min = 4, message = "Username should be min of 4 characters !!")
	private String name;

	@Email(message = "Email address is not null")
	private String email;

	@NotNull
	@Size(min = 3, max = 10, message = "Password must be min of 3 characters and max of 10 characters")
	private String password;
	@NonNull
	private String about;
	
	

}

