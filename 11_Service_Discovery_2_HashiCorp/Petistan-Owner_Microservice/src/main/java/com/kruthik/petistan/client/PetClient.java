package com.kruthik.petistan.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;

@FeignClient(name = "petistan-pet-microservice")
//@RequestMapping("/pet")
public interface PetClient {

	@PostMapping("/pet")
	Integer savePet(@RequestBody PetDTO petDTO);

	@GetMapping("/pet/{petId}")
	PetDTO getById(@PathVariable int petId);

	@PatchMapping("/pet/{petId}")
	void updatePetName(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO);

	@DeleteMapping("/pet/{petId}")
	void deletePetById(@PathVariable int petId);

}
