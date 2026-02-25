package com.kruthik.petistan.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;
import com.kruthik.petistan.service.PetService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
	
	private final RestClient restClient;
	@Value("${pet.service.base.url}")
	private String petServiceBaseUrl;
	

	@Override
	public Integer savePet(PetDTO petDTO) {
		ResponseEntity<Integer> response = restClient.post()
				.uri(petServiceBaseUrl)
				.body(petDTO)
				.retrieve()
				.toEntity(Integer.class);

		return response.getBody();
	}

	@Override
	public PetDTO findById(int id) {
		ResponseEntity<PetDTO> petDTO = restClient.get()
		.uri(petServiceBaseUrl + "/{id}", id)
		.retrieve()
		.toEntity(PetDTO.class);
		return petDTO.getBody();
	}

	@Override
	public void updatePetName(int petId, String name) {
		
		// Since pet name is a sensitive data we should not send in the URL
		UpdatePetDTO updatePetDTO = new UpdatePetDTO(name);
		
		restClient.patch()
		.uri(petServiceBaseUrl + "/{id}", petId)
		.body(updatePetDTO)
		.retrieve()
		.toBodilessEntity();
	}

	@Override
	public void deleteById(int petId) {
		restClient.delete()
		.uri(petServiceBaseUrl + "/{id}", petId)
		.retrieve()
		.toBodilessEntity();
	}

}
