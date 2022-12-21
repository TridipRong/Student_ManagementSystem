package com.platform.commons.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.platform.commons.DTO.UserDTO;
import com.platform.commons.Expectation.UserException;
import com.platform.commons.modle.User;
import com.platform.commons.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	@Override
	public UserDTO registerUser(UserDTO userDTO) throws UserException {
		
		if(userRepo.findByMobileNumber(userDTO.getMobileNumber()) != null) {
			throw new UserException("Mobile number is already Registred " + userDTO.getMobileNumber()) ;
		}
		
		userDTO.setRole("ROLE_"+userDTO.getRole());
		userDTO.setPassword(userDTO.getPassword());
		
		User user = dtoToUser(userDTO);
		user = userRepo.save(user);
		
		UserDTO registerUser = userToDTO(user);
		return registerUser;
		
	}
	
	public User dtoToUser(UserDTO userDTO) {
		return this.modelMapper.map(userDTO, User.class);
	}
	
	public UserDTO userToDTO(User user) {
		return this.modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO registerStudentAsUser(UserDTO userDTO) throws UserException {
		
		if(userRepo.findByMobileNumber(userDTO.getMobileNumber()) != null) {
			throw new UserException("Mobile number is already Registred " + userDTO.getMobileNumber()) ;
		}
		userDTO.setRole("ROLE_NORMAL");
		
		User user = dtoToUser(userDTO);
		user = userRepo.save(user);
		
		UserDTO registerUser = userToDTO(user);
		return registerUser;
		
	}

	
	
}
