package com.kruthik.petistan.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class PetStatisticsDTO {

	private long total;
	private Map<String, PetCategoryStatisticsDTO> category = new HashMap<>();

	public void incrementTotal(long count) {
		this.total += count;
	}

	public PetCategoryStatisticsDTO getOrCreateCategory(String category) {
		return this.category.computeIfAbsent(category, key -> new PetCategoryStatisticsDTO());
	}

}
