package com.kruthik.petistan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kruthik.petistan.dto.PetCategoryStatisticsDTO;
import com.kruthik.petistan.dto.PetGenderStatisticsDTO;
import com.kruthik.petistan.dto.PetStatisticsDTO;
import com.kruthik.petistan.enums.Gender;
import com.kruthik.petistan.enums.PetType;
import com.kruthik.petistan.repository.PetRepository;
import com.kruthik.petistan.service.PetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
	
	private final PetRepository petRepository;
	
	@Override
	public PetStatisticsDTO getStatistics() {
		PetStatisticsDTO petStatc = new PetStatisticsDTO();
		
		List<Object[]> rows = petRepository.fetchStatistics();
		
		for(Object[] row : rows) {
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

}
