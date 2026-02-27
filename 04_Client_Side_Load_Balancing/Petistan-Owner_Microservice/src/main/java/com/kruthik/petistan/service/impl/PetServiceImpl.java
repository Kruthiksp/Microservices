package com.kruthik.petistan.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;
import com.kruthik.petistan.service.PetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

	private final RestClient restClient;
	@Value("${pet.service.base.url}")
	private String petBaseUrl;

	@Override
	public Integer savePet(PetDTO petDTO) {
		ResponseEntity<Integer> response = restClient.post().uri(petBaseUrl).body(petDTO).retrieve()
				.toEntity(Integer.class);
		return response.getBody();
	}

	@Override
	public PetDTO getById(int petId) {
		ResponseEntity<PetDTO> response = restClient.get().uri(petBaseUrl + "/{petId}", petId).retrieve()
				.toEntity(PetDTO.class);
		return response.getBody();
	}

	@Override
	public void updatePetName(int petId, UpdatePetDTO updatePetDTO) {
		restClient.patch().uri(petBaseUrl + "/{petId}", petId).body(updatePetDTO).retrieve()
				.toBodilessEntity();
	}

	@Override
	public void deleteById(int petId) {
		restClient.delete().uri(petBaseUrl + "/{petId}", petId).retrieve().toBodilessEntity();
	}

}
