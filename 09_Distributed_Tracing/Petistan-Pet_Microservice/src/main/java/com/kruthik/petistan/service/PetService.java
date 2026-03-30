package com.kruthik.petistan.service;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.PetStatisticsDTO;

public interface PetService {

	PetStatisticsDTO getStatistics();
	
	Integer savePet(PetDTO petDTO);
	
	PetDTO getById(int petId);
	
	void updatePetName(int petId, String name);
	
	void deleteById(int petId);

}
