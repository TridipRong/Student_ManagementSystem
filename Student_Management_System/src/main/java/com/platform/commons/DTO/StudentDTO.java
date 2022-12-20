package com.platform.commons.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.platform.commons.Enums.Gender;
import com.platform.commons.modle.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	
	private Integer studentId;
	
	@NotNull(message = "name cannot be Null")
	@NotBlank(message = "name cannot be blank")
	@NotEmpty(message = "name cannot be empty")
	private String name;
	
	@NotNull(message = "fatherName cannot be Null")
	@NotBlank(message = "fatherName cannot be blank")
	@NotEmpty(message = "fatherName cannot be empty")
	private String fatherName;
	
	@Email(message = "Please enter valid emailId")
	private String email;
	
	@Pattern(regexp = "[6-9][0-9]{9}",message = "Mobile number should start with 6-9 and of size 10")
	private String mobileNumber;
	
	private LocalDate dob;
	
	@NotNull(message = "gender cannot be Null")
	private Gender gender;
	
	
	private List<Address> address = new ArrayList<>();
	
}
