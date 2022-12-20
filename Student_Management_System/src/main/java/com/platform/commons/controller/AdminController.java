package com.platform.commons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.commons.DTO.CourseDTO;
import com.platform.commons.DTO.CourseStudents;
import com.platform.commons.DTO.StudentCourse;
import com.platform.commons.DTO.StudentDTO;
import com.platform.commons.DTO.UserDTO;
import com.platform.commons.Expectation.AdminExcepttion;
import com.platform.commons.Expectation.CourseException;
import com.platform.commons.Expectation.StudentException;
import com.platform.commons.Expectation.UserException;
import com.platform.commons.service.CourseService;
import com.platform.commons.service.StudentService;
import com.platform.commons.service.UserService;

import jakarta.validation.Valid;


@RestController
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/admin/register")
	public ResponseEntity<UserDTO> registerAdminHandler(@Valid @RequestBody UserDTO userDTO	) throws AdminExcepttion, UserException {
		
		UserDTO registeredUser =  userService.registerUser(userDTO) ;
		
		return new ResponseEntity<UserDTO>(registeredUser,HttpStatus.CREATED) ;
	}
	@PostMapping("/students/")
	public ResponseEntity<StudentDTO> registerStudentHandler(@Valid @RequestBody StudentDTO studentDTO)
			throws StudentException, UserException {

		StudentDTO registredStudent = studentService.registerStudent(studentDTO);

		return new ResponseEntity<StudentDTO>(registredStudent, HttpStatus.CREATED);
	}

	
	@GetMapping("/students/")
	public ResponseEntity<List<StudentDTO>> getStudentsByNameHandler(@RequestParam("name") String name)
			throws StudentException {

		List<StudentDTO> studentsList = studentService.getStudentByName(name);

		return new ResponseEntity<List<StudentDTO>>(studentsList, HttpStatus.OK);
	}
	
	@PostMapping("/courses/")
	public ResponseEntity<CourseDTO> addCourseHandler(@Valid @RequestBody CourseDTO courseDTO) throws CourseException {
		
		CourseDTO savedCourse = courseService.addCourse(courseDTO);
		
		return new ResponseEntity<CourseDTO>(savedCourse,HttpStatus.CREATED) ;
	}
	 

	@PutMapping("/courses/assgign")
	public ResponseEntity<StudentCourse> assginStudentToCourseHandler(@RequestParam("studentId") Integer sttudentId,
																	@RequestParam("courseId") Integer courseId) throws CourseException, StudentException {
		
		StudentCourse assignedCourse =  courseService.assignCourseToStudent(sttudentId, courseId) ;
		
		return new ResponseEntity<StudentCourse>(assignedCourse,HttpStatus.OK) ;
	}
	

	@GetMapping("/courses/")
	public ResponseEntity<CourseStudents> getStudentsFromCorseHandler(@RequestParam Integer courseId) throws CourseException {
		
		CourseStudents courseStudents =  courseService.getStudentsFromCourse(courseId);
		
		return new ResponseEntity<CourseStudents>(courseStudents,HttpStatus.OK) ;
	}
	

	@DeleteMapping("/courses/")
	public ResponseEntity<CourseDTO> removeCourseHandler(@RequestParam Integer courseId) throws CourseException {
		
		CourseDTO removedCourse =  courseService.removeCourse(courseId);
		
		return new ResponseEntity<CourseDTO>(removedCourse,HttpStatus.OK) ;
	}
	
	@GetMapping("/courses/topic")
	public ResponseEntity<List<CourseDTO>> getCoursesByTopic(@RequestParam("topicName") String topicName) throws CourseException {
		
		List<CourseDTO> courses =  courseService.getCoursesByTopic(topicName);
		
		return new ResponseEntity<List<CourseDTO>>(courses,HttpStatus.OK);
	}
	
	
}
