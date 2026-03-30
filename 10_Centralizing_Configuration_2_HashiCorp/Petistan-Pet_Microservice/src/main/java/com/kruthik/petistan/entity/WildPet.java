package com.kruthik.petistan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "wild_pet")
public class WildPet extends Pet{
	
	@Column(name = "birth_place")
	private String birthPlace;
	
}
