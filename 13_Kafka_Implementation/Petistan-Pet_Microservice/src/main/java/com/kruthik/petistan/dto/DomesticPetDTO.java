package com.kruthik.petistan.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DomesticPetDTO extends PetDTO {

	private LocalDate birthDate;

}
