package com.kruthik.petistan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kruthik.petistan.dto.PetCategoryStatisticsDTO;
import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.PetGenderStatisticsDTO;
import com.kruthik.petistan.dto.PetStatisticsDTO;
import com.kruthik.petistan.entity.Pet;
import com.kruthik.petistan.enums.Gender;
import com.kruthik.petistan.enums.PetType;
import com.kruthik.petistan.exception.PetNotFoundException;
import com.kruthik.petistan.repository.PetRepository;
import com.kruthik.petistan.service.PetService;
import com.kruthik.petistan.util.PetMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

	private final PetMapper petMapper;
	private final PetRepository petRepository;

	@Override
	public PetStatisticsDTO getStatistics() {
		PetStatisticsDTO petStatc = new PetStatisticsDTO();

		List<Object[]> rows = petRepository.fetchStatistics();

		for (Object[] row : rows) {
			String category = (String) row[0];
			Gender gender = (Gender) row[1];
			PetType petType = (PetType) row[2];
			long count = (long) row[3];

			petStatc.incrementTotal(count);
			PetCategoryStatisticsDTO petCategoryStatisticsDTO = petStatc.getOrCreateCategory(category);

			petCategoryStatisticsDTO.incrementTotal(count);
			PetGenderStatisticsDTO petGenderStatisticsDTO = petCategoryStatisticsDTO.getOrCreateCategory(gender);

			petGenderStatisticsDTO.incrementTotal(count);
			petGenderStatisticsDTO.mergeOrCreateType(petType, count);
		}

		return petStatc;
	}

	@Override
	public Integer savePet(PetDTO petDTO) {
		Pet entity = petMapper.dtoToEntity(petDTO);
		Pet save = petRepository.save(entity);
		return save.getId();
	}

	@Override
	public PetDTO getById(int petId) {
		Pet pet = petRepository.findById(petId)
				.orElseThrow(() -> new PetNotFoundException("Pet Not Found with the Id: " + petId));
		return petMapper.entityToDto(pet);
	}

	@Override
	public void updatePetName(int petId, String name) {
		Pet pet = petRepository.findById(petId)
				.orElseThrow(() -> new PetNotFoundException("Pet Not Found with the Id: " + petId));
		pet.setName(name);
		petRepository.save(pet);
	}

	@Override
	public void deleteById(int petId) {
		if (petRepository.existsById(petId))
			petRepository.deleteById(petId);
		else
			throw new PetNotFoundException("Pet Not Found with the Id: " + petId);
	}

}
