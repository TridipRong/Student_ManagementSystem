package com.platform.commons.service;

import java.util.List;

import com.platform.commons.DTO.CourseDTO;
import com.platform.commons.DTO.CourseStudents;
import com.platform.commons.DTO.StudentCourse;
import com.platform.commons.Expectation.CourseException;
import com.platform.commons.Expectation.StudentException;
import com.platform.commons.modle.Courses;

public interface CourseService {

	public Courses addCourse(CourseDTO courseDTO) throws CourseException;
	
	public StudentCourse assignCourseToStudent(Integer studentId,Integer courseId) throws CourseException, StudentException;
	
	public CourseStudents getStudentsFromCourse(Integer courseId) throws CourseException;
	
	public List<CourseDTO> getCoursesByTopic(String topicName) throws CourseException;
	
	public CourseDTO removeCourse(Integer courseId) throws CourseException;
	
	
}
