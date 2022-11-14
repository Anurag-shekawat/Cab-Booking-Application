package com.masai.module;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	@Column(unique = true)
	@NotNull(message = "username cannot be null")
	@Size(min = 3,max = 10,message = "length of username must be between 3 & 10")
	private String username;
	
	@NotNull(message = "password cannot be null")
//	@JsonIgnore()
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min = 5,max = 8,message = "password length should be between 5 & 8.")
	private String password;
	
	private String address;
	
	@NotNull(message = "mobile number cannot be null")
	@Pattern(regexp = "[789]{1}[0-9]{9}",message = "invalid mobile number")
	private String mobile;
	
	@Email
	private String email;
	
}
