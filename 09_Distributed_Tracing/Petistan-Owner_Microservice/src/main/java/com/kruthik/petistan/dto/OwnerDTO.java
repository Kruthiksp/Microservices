package com.kruthik.petistan.dto;

import com.kruthik.petistan.enums.Gender;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OwnerDTO {

	@EqualsAndHashCode.Include
	private Integer id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String city;
	private String state;
	@EqualsAndHashCode.Include
	private long mobile;
	@EqualsAndHashCode.Include
	private String email;
	private PetDTO petDTO;
}
