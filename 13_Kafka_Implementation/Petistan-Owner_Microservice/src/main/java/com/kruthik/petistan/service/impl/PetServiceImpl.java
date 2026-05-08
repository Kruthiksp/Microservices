package com.kruthik.petistan.service.impl;

import org.springframework.stereotype.Service;

import com.kruthik.petistan.client.PetClient;
import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;
import com.kruthik.petistan.service.PetService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

	private final PetClient petClient;

	@Override
	public Integer savePet(PetDTO petDTO) {
		return petClient.savePet(petDTO);
	}

	@CircuitBreaker(name = "circuitBreakerForFindPet", fallbackMethod = "fallbackForFindPet")
	@Retry(name = "petRetry"/* , fallbackMethod = "fallbackForFindPet" */)
	@Override
	public PetDTO getById(int petId) {
		return petClient.getById(petId);
	}

	@Retry(name = "petRetry")
	@Override
	public void updatePetName(int petId, UpdatePetDTO updatePetDTO) {
		petClient.updatePetName(petId, updatePetDTO);
	}

	@Override
	public void deleteById(int petId) {
		petClient.deletePetById(petId);
	}

	// Fallbacks:
	private PetDTO fallbackForFindPet(int petId, Throwable e) {
		log.warn("------------ This is a Fall Back method for findPet ------------");
		return null;
	}

}
