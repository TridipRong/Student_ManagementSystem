package com.platform.commons.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.commons.DTO.CourseDTO;
import com.platform.commons.DTO.StudentAddressDTO;
import com.platform.commons.DTO.StudentCourse;
import com.platform.commons.DTO.StudentDTO;
import com.platform.commons.DTO.StudentUpdateDTO;
import com.platform.commons.Expectation.CourseException;
import com.platform.commons.Expectation.StudentException;
import com.platform.commons.Expectation.UserException;
import com.platform.commons.modle.Address;
import com.platform.commons.modle.Courses;
import com.platform.commons.modle.Students;
import com.platform.commons.repo.AddressRepo;
import com.platform.commons.repo.CourseRepo;
import com.platform.commons.repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CourseRepo courseRepo;
	
	
	@Override
	public StudentDTO registerStudent(StudentDTO studentDTO) throws StudentException, UserException {
		
		Students student = dtoToStudent(studentDTO);
		
		student = studentRepo.save(student) ;		
			
		return studentToDTO(student);
	}
	
	
	@Override
	public Students getStudentById(Integer studentId) throws StudentException {
		
		return studentRepo.findById(studentId).orElseThrow(() -> new StudentException("Invalid StudentId "+ studentId)) ;
	}

	@Override
	public List<Courses> getAllCoursesAdminPurpose(Integer studentId) throws StudentException {
		
		return studentRepo.findById(studentId).orElseThrow(() -> new StudentException("Invalid StudentId "+ studentId)).getCourses();

	}

	@Override
	public List<StudentDTO> getStudentByName(String name) throws StudentException {
		
		List<Students> students = studentRepo.getStudentsByName(name);
		
		if(students.isEmpty()) 
			throw new StudentException("No student found with name : "+name) ;

		List<StudentDTO> studentsDTO =  students
										.stream()
										.map(student -> this.studentToDTO(student)).collect(Collectors.toList()) ;

		return studentsDTO;
	}

	@Override
	public StudentDTO updateEmailAndMobile(StudentUpdateDTO dto) throws StudentException {
		
		Students student = validateStudentIdAndDOB(dto.getStudentId(), dto.getDob());
		
		if(student  == null) 
			throw new StudentException("Invalid StudentId Or DOB!") ;
		
		student.setMobileNumber(dto.getNewMobileNumber());
		student.setEmail(dto.getNewEmail());
		
		student = studentRepo.save(student) ;
		
		return studentToDTO(student);
	}


	@Override
	public StudentDTO updateStudentAddress(StudentAddressDTO addressDTO) throws StudentException {
		
		Students student =  validateStudentIdAndDOB(addressDTO.getStudentId(), addressDTO.getDob());
		
		List<Address> addressList =  student.getAddress();
		
		for(Address address : addressList) {
			if(address.getAddressId().equals(addressDTO.getAddress().getAddressId())) {
				addressRepo.save(addressDTO.getAddress()) ;
			}
		}
		
		Students updatedStudent = studentRepo.findById(student.getStudentId()).orElseThrow(() -> new StudentException("Invalid StudentId "+ student.getStudentId())) ;
		
		return studentToDTO(updatedStudent);
	}
	
	
	@Override
	public StudentDTO addNewAddress(StudentAddressDTO addressDTO) throws StudentException {
		
		Students student =  validateStudentIdAndDOB(addressDTO.getStudentId(), addressDTO.getDob());
		List<Address> addressList =  student.getAddress();
		
		List<Address> filterAddress = addressList.stream().filter(address -> address.getAddressType().equals(addressDTO.getAddress().getAddressType())).collect(Collectors.toList());
		if(filterAddress.size() != 0) 
			throw new StudentException("Address type : "+ addressDTO.getAddress().getAddressType() +" is already added!. You have to update the address") ;
		
		student.getAddress().add(addressDTO.getAddress()) ;
		Students updateStudent = studentRepo.save(student) ;
		
		return studentToDTO(updateStudent);
	}
	
	
	public Students validateStudentIdAndDOB(Integer studentId,LocalDate dob) {
		
		Optional<Students> studentOpt = studentRepo.findById(studentId);
		
		if(studentOpt.isEmpty()) 
			return null;
		
		Students student = studentOpt.get();
		
		if(!dob.equals(student.getDob())) 
			return null;
		
		return student;
	}
	
	
	
	@Override
	public StudentCourse getStudentCourses(Integer studentId, String dateOfBirth) throws StudentException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
				
		Students student = validateStudentIdAndDOB(studentId,dob) ;
		if(student == null) 
			throw new StudentException("Invalid student Id or Passord") ;
		
	
		return coursesToStudentCourse(student.getCourses(), student);
	}
	
	
	
	@Override
	public StudentCourse leaveCourse(Integer studentId, String dateOfBirth, Integer courseId) throws StudentException, CourseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
				
		Students student = validateStudentIdAndDOB(studentId,dob) ;
		Courses course = courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Invalid courseId: "+courseId)) ;
//		List<Course> courses = student.getCourses();
		
		if(student.getCourses().contains(course)) {
			student.getCourses().remove(course) ;
		}
		
		if(course.getStudents().contains(student)) {
			course.getStudents().remove(student) ;
		}
		courseRepo.save(course) ;
		
		student = studentRepo.save(student) ;
		
		return coursesToStudentCourse(student.getCourses(), student);
	}
	
	@Override
	public StudentCourse coursesToStudentCourse(List<Courses> courses,Students student) {
		
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudentId(student.getStudentId());
		studentCourse.setName(student.getName());
		
		List<CourseDTO> courseDTOList = new ArrayList<>();
		for(Courses singleCourse : student.getCourses()) {
			
			CourseDTO dto = new CourseDTO();
			BeanUtils.copyProperties(singleCourse, dto);
			courseDTOList.add(dto) ;
		}
		
		studentCourse.setCourses(courseDTOList);
		
		return studentCourse;
	}

	
	public Students dtoToStudent(StudentDTO studentDTO) {
		return this.modelMapper.map(studentDTO, Students.class);
	}
	
	public StudentDTO studentToDTO(Students student) {
		return this.modelMapper.map(student, StudentDTO.class);
	}
}








