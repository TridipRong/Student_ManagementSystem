package com.platform.commons.modle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.platform.commons.Enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	private String name;
	private String fatherName;
	private String email;
	private String mobileNumber;
	private LocalDate dob;
	private Gender gender;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "students")
	List<Courses> courses = new ArrayList<>();

	@Override
	public String toString() {
		return "Student [name=" + name + ", fatherName=" + fatherName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", dob=" + dob + ", gender=" + gender + ", address=" + address + "]";
	}
}
