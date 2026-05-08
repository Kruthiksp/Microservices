package com.kruthik.petistan.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;

@HttpExchange("/pet")
public interface PetClient {

	@PostExchange
	Integer savePet(@RequestBody PetDTO petDTO);

	@GetExchange("/{petId}")
	PetDTO getById(@PathVariable int petId);

	@PatchExchange("/{petId}")
	void updatePetName(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO);

	@DeleteExchange("/{petId}")
	void deletePetById(@PathVariable int petId);

}
