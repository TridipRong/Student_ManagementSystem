package com.platform.commons.DTO;
import java.time.LocalDate;

import com.platform.commons.modle.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAddressDTO {

	private Integer studentId;
	private LocalDate dob;
	private Address address;
}
