package com.platform.commons.modle;

import com.platform.commons.Enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@NotNull(message = "area cannot be Null")
	private String area;
	
	@NotNull(message = "city cannot be Null")
	private String city;
	
	@NotNull(message = "state cannot be Null")
	private String state;
	
	@NotNull(message = "district cannot be Null")
	private String district;
	
	@NotNull(message = "pincode cannot be Null")
	private String pincode;

	private AddressType addressType;
}
