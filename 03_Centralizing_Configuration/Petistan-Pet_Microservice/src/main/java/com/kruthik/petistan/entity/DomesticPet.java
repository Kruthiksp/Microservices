package com.kruthik.petistan.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "domestic_pet")
public class DomesticPet extends Pet {

	@Column(name = "date_of_birth")
	private LocalDate birthDate;

}
