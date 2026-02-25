package com.kruthik.petistan.service;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.PetStatisticsDTO;

public interface PetService {
	
	Integer savePet(PetDTO petDTO);

	PetDTO findById(int id);

	void updatePetName(int petId, String name);

	void deleteById(int petId);

	PetStatisticsDTO getStatistics();

}
