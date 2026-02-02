package com.kruthik.petistan.service;

import com.kruthik.petistan.dto.PetDTO;

public interface PetService {

	Integer savePet(PetDTO petDTO);

	PetDTO findById(int id);

	void updatePetName(int petId, String name);

	void deleteById(int petId);
	
}
