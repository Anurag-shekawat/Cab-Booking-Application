package com.masai.module;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {

	@NotNull(message = "Mobile No cannot be Null")
	private String mobileNo;
	
	@NotNull(message = "Password cannot be null")
	private String password;
	
	@NotNull(message = "Role cannot be null")
	private String role; // admin/customer/driver
}
