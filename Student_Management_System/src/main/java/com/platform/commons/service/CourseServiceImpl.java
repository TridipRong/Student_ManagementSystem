package com.platform.commons.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.app.model.Course;
import com.commons.app.model.Student;
import com.platform.commons.DTO.CourseDTO;
import com.platform.commons.DTO.CourseStudents;
import com.platform.commons.DTO.StudentCourse;
import com.platform.commons.DTO.StudentDTO;
import com.platform.commons.Expectation.CourseException;
import com.platform.commons.Expectation.StudentException;
import com.platform.commons.modle.Courses;
import com.platform.commons.modle.Students;
import com.platform.commons.repo.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentService studentService;
	
	@Override
	public Courses addCourse(CourseDTO courseDTO) throws CourseException {
			
//		Courses course = dtoToCourse(courseDTO);
		
		
		return courseRepo.save(courseDTO) ;
	}

	@Override
	public StudentCourse assignCourseToStudent(Integer studentId, Integer courseId) throws CourseException, StudentException {
		
		Courses course =  courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Invalid CourseId "+ courseId)) ;
		Students student = studentService.getStudentById(studentId);
				
		course.getStudents().add(student);
		Courses updatedCourseDetails = courseRepo.save(course);
		
		List<Courses> studentCourses =  studentService.getAllCoursesAdminPurpose(studentId);
		
		StudentCourse studentCourseDetails = studentService.coursesToStudentCourse(studentCourses, student);
		
		return studentCourseDetails;
	}
	
	
//	@Override
//	public CourseStudents getStudentsFromCourse(Integer courseId) throws CourseException {
//		
//		Courses course =  courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Invalid CourseId "+ courseId)) ;
//		
//		if(course.getStudents().isEmpty()) 
//			throw new CourseException("No students are present in the Course: " + course.getCourseName()) ;
//		
//		List<Students> students = course.getStudents();
////		List<StudentDTO> studentDTOs = students.stream()
////				.map(student -> this.studentToDTO(student)).collect(Collectors.toList()) ;
////		
//		CourseStudents courseStudents = new CourseStudents();
//		BeanUtils.copyProperties(course, courseStudents);
//		courseStudents.setStudentList(studentDTOs);
//		
//		return courseStudents;
//	}
	
//	@Override
//	public List<CourseDTO> getCoursesByTopic(String topicName) throws CourseException {
//		
//		List<Courses> courses = courseRepo.getCoursesByTopic(topicName);
//		List<CourseDTO> dtoList = new ArrayList<>();
//		
//		dtoList =  courses.stream().map(course -> courseToDTO(course)).collect(Collectors.toList()) ;
// 		return dtoList;
//	}
//	
	
	@Override
	public CourseDTO removeCourse(Integer courseId) throws CourseException {
		
		Courses course =  courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Invalid CourseId: "+courseId)) ;
		
		courseRepo.delete(course);
		
		return course;
	}
	
	
//	public Courses dtoToCourse(CourseDTO courseDTO) {
//		return this.modelMapper.map(courseDTO, Course.class);
//	}
//	
//	public CourseDTO courseToDTO(Course course) {
//		return this.modelMapper.map(course, CourseDTO.class);
//	}
//	
//	public StudentDTO studentToDTO(Student student) {
//		return this.modelMapper.map(student, StudentDTO.class);
//	}

}













