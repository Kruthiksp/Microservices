package com.kruthik.petistan.service;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;

public interface PetService {
	
	Integer savePet(PetDTO petDTO);
	
	PetDTO getById(int petId);
	
	void updatePetName(int petId, UpdatePetDTO updatePetDTO);
	
	void deleteById(int petId);

}
