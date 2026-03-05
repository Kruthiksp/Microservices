package com.kruthik.petistan.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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

	private final RestClient loadBalancedRestClient;
	@Value("${pet.service.base.url}")
	private String petBaseUrl;

	@Override
	public Integer savePet(PetDTO petDTO) {
		ResponseEntity<Integer> response = loadBalancedRestClient.post().uri(petBaseUrl).body(petDTO).retrieve()
				.toEntity(Integer.class);
		return response.getBody();
	}

	@CircuitBreaker(name = "circuitBreakerForFindPet", fallbackMethod = "fallbackForFindPet")
	@Retry(name = "petRetry"/* , fallbackMethod = "fallbackForFindPet" */)
	@Override
	public PetDTO getById(int petId) {
		ResponseEntity<PetDTO> response = loadBalancedRestClient.get().uri(petBaseUrl + "/{petId}", petId).retrieve()
				.toEntity(PetDTO.class);
		return response.getBody();
	}

	@Retry(name = "petRetry")
	@Override
	public void updatePetName(int petId, UpdatePetDTO updatePetDTO) {
		loadBalancedRestClient.patch().uri(petBaseUrl + "/{petId}", petId).body(updatePetDTO).retrieve()
				.toBodilessEntity();
	}

	@Override
	public void deleteById(int petId) {
		loadBalancedRestClient.delete().uri(petBaseUrl + "/{petId}", petId).retrieve().toBodilessEntity();
	}
	
	// Fallbacks:
	private PetDTO fallbackForFindPet(int petId, Throwable e) {
		log.warn("------------ This is a Fall Back method for findPet ------------");
		return null;		
	}

}
