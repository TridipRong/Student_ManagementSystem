package com.platform.commons.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.commons.modle.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByMobileNumber(String mobileNumber);
	
}
