package com.example.blog_app_apis.payloads;

import com.example.blog_app_apis.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.management.relation.RoleStatus;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {


	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
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


	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Set<RoleDto> roles = new HashSet<>();
	
	

}

