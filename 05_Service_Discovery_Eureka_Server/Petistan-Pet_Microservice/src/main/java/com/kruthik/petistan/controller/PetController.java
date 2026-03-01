package com.kruthik.petistan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.PetStatisticsDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;
import com.kruthik.petistan.service.PetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

	private final PetService petService;

	@PostMapping
	public ResponseEntity<Integer> savePet(@RequestBody PetDTO petDTO) {
		Integer savePet = petService.savePet(petDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savePet);
	}

	@GetMapping("/{petId}")
	public ResponseEntity<PetDTO> getPetById(@PathVariable int petId) {
		PetDTO petDto = petService.getById(petId);
		return ResponseEntity.status(HttpStatus.OK).body(petDto);
	}

	@PatchMapping("/{petId}")
	public ResponseEntity<PetDTO> updatePetName(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO) {
		petService.updatePetName(petId, updatePetDTO.name());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{petId}")
	public ResponseEntity<PetDTO> deletePetById(@PathVariable int petId) {
		petService.deleteById(petId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/stats")
	public ResponseEntity<PetStatisticsDTO> getStatistics() {
		PetStatisticsDTO petStatistics = petService.getStatistics();
		return ResponseEntity.status(HttpStatus.OK).body(petStatistics);
	}
}
