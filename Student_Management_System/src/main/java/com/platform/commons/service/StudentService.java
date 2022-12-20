package com.platform.commons.service;

import java.util.List;

import com.platform.commons.DTO.StudentAddressDTO;
import com.platform.commons.DTO.StudentCourse;
import com.platform.commons.DTO.StudentDTO;
import com.platform.commons.DTO.StudentUpdateDTO;
import com.platform.commons.Expectation.CourseException;
import com.platform.commons.Expectation.StudentException;
import com.platform.commons.Expectation.UserException;
import com.platform.commons.modle.Courses;
import com.platform.commons.modle.Students;

public interface StudentService {

	public StudentDTO registerStudent(StudentDTO studentDTO) throws StudentException, UserException;
	
	public Students getStudentById(Integer studentId) throws StudentException ;
	
	public List<Courses> getAllCoursesAdminPurpose(Integer studentId) throws StudentException ;
	
	public List<StudentDTO> getStudentByName(String name) throws StudentException;
	
	public StudentDTO updateEmailAndMobile(StudentUpdateDTO dto) throws StudentException;
	
	public StudentDTO updateStudentAddress(StudentAddressDTO addressDTO) throws StudentException;
	
	public StudentCourse getStudentCourses(Integer studentId,String dateOfBirth) throws StudentException;
	
	public StudentCourse leaveCourse(Integer studentId,String dateOfBirth,Integer courseId) throws StudentException, CourseException;
	
	public StudentCourse coursesToStudentCourse(List<Courses> courses,Students student) ;
	
	public StudentDTO addNewAddress(StudentAddressDTO addressDTO) throws StudentException;
}
