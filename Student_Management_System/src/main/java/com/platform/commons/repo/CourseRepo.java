package com.platform.commons.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platform.commons.modle.Courses;

@Repository
public interface CourseRepo extends JpaRepository<Courses, Integer> {

	@Query("from Course c where c.topics LIKE %:topic% OR c.courseName LIKE %:topic% ")
	public List<Courses> getCoursesByTopic(String topic) ;
}
