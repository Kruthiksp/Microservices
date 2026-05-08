package com.kruthik.petistan.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kruthik.petistan.enums.Gender;
import com.kruthik.petistan.enums.PetType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({
		@Type(value = DomesticPetDTO.class, name = "Domestic"),
		@Type(value = WildPetDTO.class, name = "Wild") 
	})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class PetDTO {

	@EqualsAndHashCode.Include
	private Integer id;
	private String name;
	private Gender gender;
	private PetType type;

}
