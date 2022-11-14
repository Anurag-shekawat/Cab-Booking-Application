package com.masai.service;

import com.masai.exception.LoginException;
import com.masai.module.LoginDTO;

public interface LoginService {

	public String logIntoAccount(LoginDTO dto) throws LoginException;

	public String logOutFromAccount(String key) throws LoginException;

}
