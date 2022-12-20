package com.platform.commons.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platform.commons.modle.Students;

@Repository
public interface StudentRepo extends JpaRepository<Students, Integer> {

	@Query("from Student s where s.name LIKE %:name% ") 
	public List<Students> getStudentsByName(String name) ;
	
}
