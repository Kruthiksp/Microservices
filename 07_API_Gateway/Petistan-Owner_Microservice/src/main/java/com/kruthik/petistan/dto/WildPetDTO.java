package com.kruthik.petistan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WildPetDTO extends PetDTO {

	private String birthPlace;

}
