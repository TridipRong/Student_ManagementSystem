package com.platform.commons.service;
import com.platform.commons.DTO.UserDTO;
import com.platform.commons.Expectation.UserException;

public interface UserService {

	public UserDTO registerUser(UserDTO userDTO) throws UserException;
	
	public UserDTO registerStudentAsUser(UserDTO userDTO) throws UserException;
	
}
