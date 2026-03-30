package com.kruthik.petistan.entity;

import com.kruthik.petistan.enums.Gender;
import com.kruthik.petistan.enums.PetType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
@Getter
@Setter
public abstract class Pet extends BaseEntity{

	@Column(nullable = false)
	private String name;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private PetType type;

}
