package com.kruthik.petistan.dto;

import java.util.EnumMap;
import java.util.Map;

import com.kruthik.petistan.enums.Gender;

import lombok.Data;

@Data
public class PetCategoryStatisticsDTO {

	private long total;
	private Map<Gender, PetGenderStatisticsDTO> gender = new EnumMap<>(Gender.class);
	
	public void incrementTotal(long count) {
		this.total += count;
	}
	
	public PetGenderStatisticsDTO getOrCreateCategory(Gender gender) {
		return this.gender.computeIfAbsent(gender, key -> new PetGenderStatisticsDTO());
	}
}
